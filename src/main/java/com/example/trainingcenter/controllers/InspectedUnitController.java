package com.example.trainingcenter.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.trainingcenter.models.Employee;
import com.example.trainingcenter.models.EquipmentUnit;
import com.example.trainingcenter.models.InspectedUnit;
import com.example.trainingcenter.models.InventoryCommission;
import com.example.trainingcenter.models.User;
import com.example.trainingcenter.services.EmployeeService;
import com.example.trainingcenter.services.InspectedUnitService;

@Controller
@RequestMapping("/inspected_unit")
@PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'EMPLOYEE')")
public class InspectedUnitController {
    private final InspectedUnitService inspectedUnitService;
    private final EmployeeService employeeService;

    public InspectedUnitController(InspectedUnitService inspectedUnitService, EmployeeService employeeService) {
        this.inspectedUnitService = inspectedUnitService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String show(Model model) {
        loadList(model);
        return "inspected_unit_control";
    }

    private void loadList(Model model){
        List<InspectedUnit> needed = new ArrayList<InspectedUnit>();

        // getting of current user
        Employee currentEmployee = employeeService.getByUser((User)SecurityContextHolder.getContext()
            .getAuthentication().getPrincipal());
        needed = inspectedUnitService.getByEmployee(currentEmployee);
        
        // getting of last inventory commission for current user 
        InventoryCommission currentInventoryCommission = new InventoryCommission();
        if (!needed.isEmpty()) currentInventoryCommission 
            = needed.get(needed.size() - 1)
                .getInventoryCommission();
        needed = inspectedUnitService.getByInventoryCommissionAndEmployee(currentInventoryCommission, currentEmployee);
        
        // getting equipment units for current user & last inventory commission
        List<EquipmentUnit> equipmentUnits = new ArrayList<EquipmentUnit>();
        for (InspectedUnit inspectedUnit : needed) equipmentUnits.add(inspectedUnit.getEquipmentUnit());

        model.addAttribute("equipmentUnits", equipmentUnits);
    }
}
