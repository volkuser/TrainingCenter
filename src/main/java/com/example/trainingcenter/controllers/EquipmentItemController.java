package com.example.trainingcenter.controllers;

import com.example.trainingcenter.models.Equipment;
import com.example.trainingcenter.models.EquipmentType;
import com.example.trainingcenter.models.Unit;
import com.example.trainingcenter.repositories.EquipmentTypeRepository;
import com.example.trainingcenter.repositories.UnitRepository;
import com.example.trainingcenter.services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/equipment/more")
@PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
public class EquipmentItemController {
    private final EquipmentService equipmentService;
    private final UnitRepository unitRepository;
    private final EquipmentTypeRepository equipmentTypeRepository;

    public EquipmentItemController(EquipmentService equipmentService, UnitRepository unitRepository, EquipmentTypeRepository equipmentTypeRepository) {
        this.equipmentService = equipmentService;
        this.unitRepository = unitRepository;
        this.equipmentTypeRepository = equipmentTypeRepository;
    }

    @GetMapping("/{id}")
    public String more(@PathVariable("id") String id, Model model){
        getAndLoad(model, id);
        return "equipment_item_control";
    }

    private void getAndLoad(Model model, String id){
        List<Unit> units = StreamSupport.stream(unitRepository.findAll().spliterator(), false).toList();
        model.addAttribute("units", units);
        List<EquipmentType> equipmentTypes = StreamSupport.stream(equipmentTypeRepository.findAll().spliterator(),
                false).toList();
        model.addAttribute("equipmentTypes", equipmentTypes);

        Equipment equipment = equipmentService.getById(Long.parseLong(id));
        model.addAttribute("selectedEquipment", equipment);
    }

    @PostMapping("/{id}")
    public String update(@Valid @ModelAttribute(value = "selectedEquipment") Equipment equipment,
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

        getAndLoad(model, equipment.getId().toString());
        return "equipment_item_control";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") String id, Model model) {
        try {
            equipmentService.deleteById(Long.parseLong(id));
            return "redirect:/equipment";
        } catch (Exception exception) {
            getAndLoad(model, id);
            return "redirect:/equipment/more/{id}";
        }
    }
}
