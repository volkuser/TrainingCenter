package com.example.trainingcenter.controllers;

import com.example.trainingcenter.models.TrainingCenter;
import com.example.trainingcenter.repositories.TrainingCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/training_center/more")
@PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
public class TrainingCenterItemController {
    @Autowired
    private TrainingCenterRepository trainingCenterRepository;

    @GetMapping("/{id}")
    public String more(@PathVariable("id") String id, Model model){
        getAndLoad(model, id);
        return "training_center_item_control";
    }

    private void getAndLoad(Model model, String id){
        TrainingCenter trainingCenter = trainingCenterRepository.findById(Long.parseLong(id)).orElseThrow();
        model.addAttribute("selectedTrainingCenter", trainingCenter);
    }

    @PostMapping("/{id}")
    public String update(@Valid @ModelAttribute(value = "selectedTrainingCenter") TrainingCenter trainingCenter,
                         BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else trainingCenterRepository.save(trainingCenter);

        getAndLoad(model, trainingCenter.getId().toString());
        return "training_center_item_control";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") String id, Model model){
        try{
            trainingCenterRepository.deleteById(Long.parseLong(id));
            return "redirect:/training_center";
        } catch (Exception exception) {
            getAndLoad(model, id);
            return "redirect:/training_center/more/{id}";
        }
    }
}
