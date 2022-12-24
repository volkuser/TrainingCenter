package com.example.trainingcenter.controllers;

import com.example.trainingcenter.models.EquipmentType;
import com.example.trainingcenter.repositories.EquipmentTypeRepository;
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
@RequestMapping("/equipment_type")
@PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
public class EquipmentTypeController {
    private final EquipmentTypeRepository equipmentTypeRepository;

    public EquipmentTypeController(EquipmentTypeRepository equipmentTypeRepository) {
        this.equipmentTypeRepository = equipmentTypeRepository;
    }

    @GetMapping
    public String show(Model model) {
        loadList(model);
        return "equipment_type_control";
    }

    private void loadList(Model model){
        List<EquipmentType> equipmentTypes = StreamSupport.stream(equipmentTypeRepository.findAll().spliterator(),
                false).toList();
        model.addAttribute("equipmentTypes", equipmentTypes);
    }

    @PostMapping
    public String add(@Valid @ModelAttribute(value = "selectedEquipmentType") EquipmentType equipmentType,
                      BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else equipmentTypeRepository.save(equipmentType);

        loadList(model);
        return "equipment_type_control";
    }
}