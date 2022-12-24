package com.example.trainingcenter.controllers;

import com.example.trainingcenter.models.EquipmentType;
import com.example.trainingcenter.repositories.EquipmentTypeRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/equipment_type/more")
@PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
public class EquipmentTypeItemController {
    private final EquipmentTypeRepository equipmentTypeRepository;

    public EquipmentTypeItemController(EquipmentTypeRepository equipmentTypeRepository) {
        this.equipmentTypeRepository = equipmentTypeRepository;
    }

    @GetMapping("/{id}")
    public String more(@PathVariable("id") String id, Model model){
        getAndLoad(model, id);
        return "equipment_type_item_control";
    }

    private void getAndLoad(Model model, String id){
        EquipmentType equipmentType = equipmentTypeRepository.findById(Long.parseLong(id)).orElseThrow();
        model.addAttribute("selectedEquipmentType", equipmentType);
    }

    @PostMapping("/{id}")
    public String update(@Valid @ModelAttribute("selectedEquipmentType") EquipmentType equipmentType,
                         BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else equipmentTypeRepository.save(equipmentType);

        getAndLoad(model, equipmentType.getId().toString());
        return "equipment_type_item_control";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") String id, Model model){
        try{
            equipmentTypeRepository.deleteById(Long.parseLong(id));
            return "redirect:/equipment_type";
        } catch (Exception exception) {
            getAndLoad(model, id);
            return "redirect:/equipment_type/more/{id}";
        }
    }
}
