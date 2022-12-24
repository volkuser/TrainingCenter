package com.example.trainingcenter.controllers;

import com.example.trainingcenter.models.*;
import com.example.trainingcenter.repositories.EquipmentTypeRepository;
import com.example.trainingcenter.repositories.UnitRepository;
import com.example.trainingcenter.services.EquipmentService;
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
@RequestMapping("/equipment")
@PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
public class EquipmentController {
    private final EquipmentService equipmentService;
    private final UnitRepository unitRepository;
    private final EquipmentTypeRepository equipmentTypeRepository;

    public EquipmentController(EquipmentService equipmentService, UnitRepository unitRepository, EquipmentTypeRepository equipmentTypeRepository) {
        this.equipmentService = equipmentService;
        this.unitRepository = unitRepository;
        this.equipmentTypeRepository = equipmentTypeRepository;
    }

    @GetMapping
    public String show(Model model) {
        loadListWithSubList(model);
        return "equipment_control";
    }

    private void loadSubList(Model model){
        List<Unit> units = StreamSupport.stream(unitRepository.findAll().spliterator(), false).toList();
        model.addAttribute("units", units);
        List<EquipmentType> equipmentTypes = StreamSupport.stream(equipmentTypeRepository.findAll().spliterator(),
                false).toList();
        model.addAttribute("equipmentTypes", equipmentTypes);
    }

    private void loadListWithSubList(Model model){
        List<com.example.trainingcenter.models.Equipment> equipments = StreamSupport.stream(equipmentService.getAll().spliterator(),
                false).toList();
        model.addAttribute("equipments", equipments);

        loadSubList(model);
    }

    @PostMapping
    public String add(@Valid @ModelAttribute(value = "selectedEquipment") com.example.trainingcenter.models.Equipment equipment,
                      BindingResult bindingResult, Model model, // specific is down
                      @RequestParam(value = "unit") String unitAsString,
                      @RequestParam(value = "equipmentType") String equipmentTypeId){
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else {
            try {
                equipment.setEquipmentType(equipmentTypeRepository
                        .findById(Long.parseLong(equipmentTypeId)).orElseThrow());
                equipment.setUnit(unitRepository
                        .findById(Long.parseLong(unitAsString)).orElseThrow());
            } catch (Exception ignored) {
                equipment.setEquipmentType(equipmentService.getById(equipment.getId()).getEquipmentType());
                equipment.setUnit(equipmentService.getById(equipment.getId()).getUnit());
            }
            equipmentService.save(equipment);
        }

        loadListWithSubList(model);
        return "equipment_control";
    }
}
