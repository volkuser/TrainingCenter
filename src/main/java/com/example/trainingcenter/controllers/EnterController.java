package com.example.trainingcenter.controllers;

import com.example.trainingcenter.models.Employee;
import com.example.trainingcenter.models.User;
import com.example.trainingcenter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EnterController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String firstSetup(Model model) {
        return "/login";
    }

    @GetMapping("/home")
    public String show(Model model) {
        return "/home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(User user, Employee employee, Model model){
        if (!userService.createUser(user, employee)) model.addAttribute("errorMessage",
                "user with this email already exists");
        userService.createUser(user, employee);
        return "redirect:/login";
    }
}
