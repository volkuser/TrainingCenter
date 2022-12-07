package com.example.trainingcenter.controllers;

import com.example.trainingcenter.models.Location;
import com.example.trainingcenter.models.TrainingCenter;
import com.example.trainingcenter.repositories.TrainingCenterRepository;
import com.example.trainingcenter.services.LocationService;
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
@RequestMapping("/location/more")
@PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'EMPLOYEE')")
public class LocationItemController {
    @Autowired
    private LocationService locationService;
    @Autowired
    private TrainingCenterRepository trainingCenterRepository;

    @GetMapping("/{id}")
    public String more(@PathVariable("id") String id, Model model){
        getAndLoad(model, id);
        return "location_item_control";
    }

    private void getAndLoad(Model model, String id){
        List<TrainingCenter> trainingCenters = StreamSupport.stream(trainingCenterRepository.findAll().spliterator(),
                false).toList();
        model.addAttribute("trainingCenters", trainingCenters);

        Location location = locationService.getById(Long.parseLong(id));
        model.addAttribute("selectedLocation", location);
    }

    @PostMapping("/{id}")
    public String update(@Valid @ModelAttribute(value = "selectedLocation") Location location,
                         BindingResult bindingResult, Model model, // specific is down
                         @RequestParam(value = "trainingCenter") String trainingCenterAsString){
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else {
            try {
                location.setTrainingCenter(trainingCenterRepository
                        .findById(Long.parseLong(trainingCenterAsString)).orElseThrow());
            } catch (Exception ignored) {
                location.setTrainingCenter(locationService.getById(location.getId()).getTrainingCenter());
            }
            locationService.save(location);
        }

        getAndLoad(model, location.getId().toString());
        return "location_item_control";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") String id, Model model) {
        try {
            locationService.deleteById(Long.parseLong(id));
            return "redirect:/location";
        } catch (Exception exception) {
            getAndLoad(model, id);
            return "redirect:/location/more/{id}";
        }
    }
}
