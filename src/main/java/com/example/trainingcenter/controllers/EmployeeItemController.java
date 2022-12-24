package com.example.trainingcenter.controllers;

import com.example.trainingcenter.models.Employee;
import com.example.trainingcenter.services.EmployeeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/employee/more")
@PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'HR')")
public class EmployeeItemController {
    private final EmployeeService employeeService;

    public EmployeeItemController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public String more(@PathVariable("id") String id, Model model){
        getAndLoad(model, id);
        return "employee_item_control";
    }

    private void getAndLoad(Model model, String id){
        Employee employee = employeeService.getById(Long.parseLong(id));
        model.addAttribute("selectedEmployee", employee);
    }

    @PostMapping("/{id}")
    public String update(@Valid @ModelAttribute("selectedEmployee") Employee employee,
                         BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else employeeService.save(employee);

        getAndLoad(model, employee.getId().toString());
        return "employee_item_control";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") String id, Model model){
        try{
            employeeService.deleteById(Long.parseLong(id));
            return "redirect:/employee";
        } catch (Exception exception) {
            getAndLoad(model, id);
            return "redirect:/employee/more/{id}";
        }
    }
}
