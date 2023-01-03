package com.example.trainingcenter.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.trainingcenter.models.Employee;
import com.example.trainingcenter.models.EquipmentUnit;
import com.example.trainingcenter.models.InspectedUnit;
import com.example.trainingcenter.models.InventoryCommission;
/* import com.example.trainingcenter.models.User; */
import com.example.trainingcenter.repositories.InventoryCommissionRepository;
import com.example.trainingcenter.repositories.UserRepository;
import com.example.trainingcenter.services.EmployeeService;
import com.example.trainingcenter.services.InspectedUnitService;
/* import com.example.trainingcenter.services.UserService; */

@Controller
@RequestMapping("/inspected_unit")
@PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'EMPLOYEE')")
public class InspectedUnitController {
    private final InspectedUnitService inspectedUnitService;
    private final EmployeeService employeeService;
    private final UserRepository userRepository;
    private final InventoryCommissionRepository inventoryCommissionRepository;

    public InspectedUnitController(InspectedUnitService inspectedUnitService, EmployeeService employeeService,
        UserRepository userRepository, InventoryCommissionRepository inventoryCommissionRepository) {
        this.inspectedUnitService = inspectedUnitService;
        this.employeeService = employeeService;
        this.userRepository = userRepository;
        this.inventoryCommissionRepository = inventoryCommissionRepository;
    }

    @GetMapping
    public String show(Model model) {
        loadList(model);
        return "inspected_unit_control";
    }

    private void loadList(Model model){
        List<InspectedUnit> needed = new ArrayList<InspectedUnit>();

        // getting of current user
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) username = ((UserDetails)principal).getUsername();
        else username = principal.toString();
        Employee currentEmployee = employeeService.getByUser(userRepository.findByEmail(username));
        needed = inspectedUnitService.getByEmployee(currentEmployee);
        
        // getting of last inventory commission
        InventoryCommission currentInventoryCommission = StreamSupport.stream(inventoryCommissionRepository.findAll()
            .spliterator(), false).toList().get(StreamSupport.stream(inventoryCommissionRepository.findAll()
            .spliterator(), false).toList().size() - 1);
        needed = inspectedUnitService.getByInventoryCommissionAndEmployee(currentInventoryCommission, currentEmployee);
        /* // getting of last inventory commission for current user 
        InventoryCommission currentInventoryCommission = new InventoryCommission();
        if (!needed.isEmpty()) {
            currentInventoryCommission = needed.get(needed.size() - 1).getInventoryCommission();
            needed = inspectedUnitService.getByInventoryCommissionAndEmployee(currentInventoryCommission, currentEmployee);
        } */
        // getting equipment units for current user & hisself last inventory commission
        List<EquipmentUnit> equipmentUnits = new ArrayList<EquipmentUnit>();
        for (InspectedUnit inspectedUnit : needed) equipmentUnits.add(inspectedUnit.getEquipmentUnit());

        model.addAttribute("equipmentUnits", equipmentUnits);
    }
}
