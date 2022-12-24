package com.example.trainingcenter.controllers;

import com.example.trainingcenter.models.*;
import com.example.trainingcenter.repositories.AvailabilityRepository;
import com.example.trainingcenter.services.EquipmentService;
import com.example.trainingcenter.services.EquipmentUnitService;
import com.example.trainingcenter.services.LocationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/equipment_unit/more")
@PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'EMPLOYEE')")
public class EquipmentUnitItemController {
    private final EquipmentUnitService equipmentUnitService;
    private final LocationService locationService;
    private final EquipmentService equipmentService;
    private final AvailabilityRepository availabilityRepository;

    public EquipmentUnitItemController(EquipmentUnitService equipmentUnitService, LocationService locationService, EquipmentService equipmentService, AvailabilityRepository availabilityRepository) {
        this.equipmentUnitService = equipmentUnitService;
        this.locationService = locationService;
        this.equipmentService = equipmentService;
        this.availabilityRepository = availabilityRepository;
    }

    @GetMapping("/{id}")
    public String more(@PathVariable("id") String id, Model model){
        getAndLoad(model, id);
        return "equipment_unit_item_control";
    }

    private void getAndLoad(Model model, String id){
        List<Location> locations = StreamSupport.stream(locationService.getAll().spliterator(),
                false).toList();
        model.addAttribute("locations", locations);
        List<Equipment> equipments = StreamSupport.stream(equipmentService.getAll().spliterator(),
                false).toList();
        model.addAttribute("equipments", equipments);
        List<Availability> availabilities = StreamSupport.stream(availabilityRepository.findAll().spliterator(),
                false).toList();
        model.addAttribute("availabilities", availabilities);

        EquipmentUnit equipmentUnit = equipmentUnitService.getById(Long.parseLong(id));
        model.addAttribute("selectedEquipmentUnit", equipmentUnit);
    }

    @PostMapping("/{id}")
    public String update(@Valid @ModelAttribute(value = "selectedEquipmentUnit") EquipmentUnit equipmentUnit,
                         BindingResult bindingResult, Model model, // specific is down
                         @RequestParam(value = "equipment") String equipment,
                         @RequestParam(value = "availability") String availability,
                         @RequestParam(value = "location") String location){
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else {
            try {
                equipmentUnit.setEquipment(equipmentService
                        .getById(Long.parseLong(equipment)));
                equipmentUnit.setAvailability(availabilityRepository
                        .findById(Long.parseLong(availability)).orElseThrow());
                equipmentUnit.setLocation(locationService
                        .getById(Long.parseLong(location)));
            } catch (Exception ignored) {
                equipmentUnit.setEquipment(equipmentUnitService.getById(equipmentUnit.getId()).getEquipment());
                equipmentUnit.setAvailability(equipmentUnitService.getById(equipmentUnit.getId()).getAvailability());
                equipmentUnit.setLocation(equipmentUnitService.getById(equipmentUnit.getId()).getLocation());
            }
            equipmentUnitService.save(equipmentUnit);
        }

        getAndLoad(model, equipmentUnit.getId().toString());
        return "equipment_unit_item_control";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") String id, Model model) {
        try {
            equipmentUnitService.deleteById(Long.parseLong(id));
            return "redirect:/equipment_unit";
        } catch (Exception exception) {
            getAndLoad(model, id);
            return "redirect:/equipment_unit/more/{id}";
        }
    }
}
