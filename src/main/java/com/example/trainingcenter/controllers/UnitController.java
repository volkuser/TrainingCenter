package com.example.trainingcenter.controllers;

import com.example.trainingcenter.models.Unit;
import com.example.trainingcenter.repositories.UnitRepository;
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
@RequestMapping("/unit")
@PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
public class UnitController {
    @Autowired
    private UnitRepository unitRepository;

    @GetMapping
    public String show(Model model) {
        loadList(model);
        return "unit_control";
    }

    private void loadList(Model model){
        List<Unit> units = StreamSupport.stream(unitRepository.findAll().spliterator(),
                false).toList();
        model.addAttribute("units", units);
    }

    @PostMapping
    public String add(@Valid @ModelAttribute(value = "selectedUnit") Unit unit,
                      BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else unitRepository.save(unit);

        loadList(model);
        return "unit_control";
    }
}
