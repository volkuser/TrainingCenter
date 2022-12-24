package com.example.trainingcenter.controllers;

import com.example.trainingcenter.models.*;
import com.example.trainingcenter.models.EquipmentUnit;
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
@RequestMapping("/equipment_unit")
@PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'EMPLOYEE')")
public class EquipmentUnitController {
    private final EquipmentUnitService equipmentUnitService;
    private final LocationService locationService;
    private final EquipmentService equipmentService;
    private final AvailabilityRepository availabilityRepository;

    public EquipmentUnitController(EquipmentUnitService equipmentUnitService, LocationService locationService, EquipmentService equipmentService, AvailabilityRepository availabilityRepository) {
        this.equipmentUnitService = equipmentUnitService;
        this.locationService = locationService;
        this.equipmentService = equipmentService;
        this.availabilityRepository = availabilityRepository;
    }

    @GetMapping
    public String show(Model model) {
        loadListWithSubList(model);
        return "equipment_unit_control";
    }

    private void loadSubList(Model model){
        List<Location> locations = StreamSupport.stream(locationService.getAll().spliterator(),
                false).toList();
        model.addAttribute("locations", locations);
        List<Equipment> equipments = StreamSupport.stream(equipmentService.getAll().spliterator(),
                false).toList();
        model.addAttribute("equipments", equipments);
        List<Availability> availabilities = StreamSupport.stream(availabilityRepository.findAll().spliterator(),
                false).toList();
        model.addAttribute("availabilities", availabilities);
    }

    private void loadListWithSubList(Model model){
        List<EquipmentUnit> equipmentUnits = StreamSupport.stream(equipmentUnitService.getAll().spliterator(),
                false).toList();
        model.addAttribute("equipmentUnits", equipmentUnits);

        loadSubList(model);
    }

    @GetMapping("/exact")
    public String exactSearch(@RequestParam(value = "query" ) String query, Model model){
        if (!query.isEmpty()){
            List<EquipmentUnit> equipmentUnits = equipmentUnitService.getResultExactSearch(query);

            loadSubList(model);

            model.addAttribute("equipmentUnits", equipmentUnits);

            return "equipment_unit_control";
        } else return "redirect:/equipment_unit";
    }

    @GetMapping("/imprecise")
    public String impreciseSearch(@RequestParam(value = "query" ) String query, Model model){
        if (!query.isEmpty()){
            List<EquipmentUnit> equipmentUnits = equipmentUnitService.getResultImpreciseSearch(query);

            loadSubList(model);

            model.addAttribute("equipmentUnits", equipmentUnits);

            return "equipment_unit_control";
        } else return "redirect:/equipment_unit";
    }

    @PostMapping
    public String add(@Valid @ModelAttribute(value = "selectedEquipmentUnit") EquipmentUnit equipmentUnit,
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

        loadListWithSubList(model);
        return "equipment_unit_control";
    }
}
