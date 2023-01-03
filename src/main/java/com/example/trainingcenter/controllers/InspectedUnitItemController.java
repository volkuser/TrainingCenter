package com.example.trainingcenter.controllers;

import java.util.stream.StreamSupport;

import com.example.trainingcenter.models.*;
import com.example.trainingcenter.repositories.AvailabilityRepository;
import com.example.trainingcenter.services.EquipmentUnitService;
import com.example.trainingcenter.services.LocationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/inspected_unit/more/{id}")
@PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'EMPLOYEE')")
public class InspectedUnitItemController {
    private final EquipmentUnitService equipmentUnitService;
    private final LocationService locationService;
    private final AvailabilityRepository availabilityRepository;

    public InspectedUnitItemController(EquipmentUnitService equipmentUnitService, LocationService locationService, 
        AvailabilityRepository availabilityRepository) {
        this.equipmentUnitService = equipmentUnitService;
        this.locationService = locationService;
        this.availabilityRepository = availabilityRepository;
    }

    @GetMapping
    public String more(@PathVariable("id") String id, Model model){
        getAndLoad(model, id);
        return "inspected_unit_item_control";
    }

    private void getAndLoad(Model model, String id){
        List<Location> locations = StreamSupport.stream(locationService.getAll().spliterator(),
                false).toList();
        model.addAttribute("locations", locations);
        List<Availability> availabilities = StreamSupport.stream(availabilityRepository.findAll().spliterator(),
                false).toList();
        model.addAttribute("availabilities", availabilities);

        EquipmentUnit equipmentUnit = equipmentUnitService.getById(Long.parseLong(id));
        model.addAttribute("selectedEquipmentUnit", equipmentUnit);
    }

    @PostMapping
    public String update(@ModelAttribute(value = "selectedEquipmentUnit") EquipmentUnit equipmentUnit, 
                         Model model, @PathVariable("id") String id, // specific is down
                         @RequestParam(value = "availability") String availability,
                         @RequestParam(value = "location") String location){
        equipmentUnit.setInventoryNumber(equipmentUnitService.getById(equipmentUnit.getId()).getInventoryNumber());                    
        equipmentUnit.setEquipment(equipmentUnitService.getById(equipmentUnit.getId()).getEquipment());                        
        equipmentUnit.setAvailability(availabilityRepository.findById(Long.parseLong(availability)).orElseThrow());
        equipmentUnit.setLocation(locationService.getById(Long.parseLong(location)));
        equipmentUnitService.save(equipmentUnit);

        getAndLoad(model, id);
        return "inspected_unit_item_control";
    }
}
