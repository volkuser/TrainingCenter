package com.example.trainingcenter.controllers;

import com.example.trainingcenter.models.TrainingCenter;
import com.example.trainingcenter.repositories.TrainingCenterRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/training_center")
@PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
public class TrainingCenterController {
    private final TrainingCenterRepository trainingCenterRepository;

    public TrainingCenterController(TrainingCenterRepository trainingCenterRepository) {
        this.trainingCenterRepository = trainingCenterRepository;
    }

    @GetMapping
    public String show(Model model) {
        loadList(model);
        return "training_center_control";
    }

    private void loadList(Model model){
        List<TrainingCenter> trainingCenters = StreamSupport.stream(trainingCenterRepository.findAll().spliterator(),
                false).toList();
        model.addAttribute("trainingCenters", trainingCenters);
    }

    @PostMapping
    public String add(@Valid @ModelAttribute(value = "selectedTrainingCenter") TrainingCenter trainingCenter,
                      BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else trainingCenterRepository.save(trainingCenter);

        loadList(model);
        return "training_center_control";
    }
}
