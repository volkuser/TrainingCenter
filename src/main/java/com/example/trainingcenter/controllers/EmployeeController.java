package com.example.trainingcenter.controllers;

import com.example.trainingcenter.models.Employee;
import com.example.trainingcenter.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@Controller
@PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public String show(Model model) {
        loadList(model);
        return "employee_control";
    }

    private void loadList(Model model){
        List<Employee> employees = StreamSupport.stream(employeeService.getAll().spliterator(),
                false).toList();
        model.addAttribute("employees", employees);
    }

    @GetMapping("/employee/exact")
    public String exactSearch(@RequestParam(value = "query" ) String query, Model model){
        if (!query.isEmpty()){
            List<Employee> employees = employeeService.getResultExactSearch(query);

            model.addAttribute("employees", employees);

            return "employee_control";
        } else return "redirect:/employee";
    }

    @GetMapping("/employee/imprecise")
    public String impreciseSearch(@RequestParam(value = "query" ) String query, Model model){
        if (!query.isEmpty()){
            List<Employee> employees = employeeService.getResultImpreciseSearch(query);

            model.addAttribute("employees", employees);

            return "employee_control";
        } else return "redirect:/employee";
    }

    @PostMapping("/employee")
    public String add(@Valid @ModelAttribute(value = "selectedEmployee") Employee employee,
                      BindingResult bindingResult, Model model, // specific is down
                      @RequestParam(value = "user", required = false) String userAsString){
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else employeeService.save(employee);

        loadList(model);
        return "employee_control";
    }
}

