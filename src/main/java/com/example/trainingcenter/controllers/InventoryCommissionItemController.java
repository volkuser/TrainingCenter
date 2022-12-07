package com.example.trainingcenter.controllers;

import com.example.trainingcenter.models.InventoryCommission;
import com.example.trainingcenter.repositories.InventoryCommissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/inventory_commission/more")
@PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
public class InventoryCommissionItemController {
    @Autowired
    private InventoryCommissionRepository inventoryCommissionRepository;

    @GetMapping("/{id}")
    public String more(@PathVariable("id") String id, Model model){
        getAndLoad(model, id);
        return "inventory_commission_item_control";
    }

    private void getAndLoad(Model model, String id){
        InventoryCommission inventoryCommission
                = inventoryCommissionRepository.findById(Long.parseLong(id)).orElseThrow();
        model.addAttribute("selectedInventoryCommission", inventoryCommission);
    }

    @PostMapping("/{id}")
    public String update(@Valid @ModelAttribute(value = "selectedInventoryCommission") InventoryCommission inventoryCommission,
                         BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else inventoryCommissionRepository.save(inventoryCommission);

        getAndLoad(model, inventoryCommission.getId().toString());
        return "inventory_commission_item_control";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") String id, Model model){
        try{
            inventoryCommissionRepository.deleteById(Long.parseLong(id));
            return "redirect:/inventory_commission";
        } catch (Exception exception) {
            getAndLoad(model, id);
            return "redirect:/inventory_commission/more/{id}";
        }
    }
}
