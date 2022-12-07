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
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/inventory_commission")
@PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
public class InventoryCommissionController {
    @Autowired
    private InventoryCommissionRepository inventoryCommissionRepository;

    @GetMapping
    public String show(Model model) {
        loadList(model);
        return "inventory_commission_control";
    }

    private void loadList(Model model){
        List<InventoryCommission> inventoryCommissions
                = StreamSupport.stream(inventoryCommissionRepository.findAll().spliterator(), false).toList();
        model.addAttribute("inventoryCommissions", inventoryCommissions);
    }

    @PostMapping
    public String add(@Valid @ModelAttribute(value = "selectedInventoryCommission") InventoryCommission inventoryCommission,
                      BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else inventoryCommissionRepository.save(inventoryCommission);

        loadList(model);
        return "inventory_commission_control";
    }
}
