package com.example.trainingcenter.controllers;

import com.example.trainingcenter.models.Location;
import com.example.trainingcenter.models.TrainingCenter;
import com.example.trainingcenter.repositories.TrainingCenterRepository;
import com.example.trainingcenter.services.LocationService;
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
@RequestMapping("/location")
@PreAuthorize("hasAnyAuthority('ADMINISTRATOR', 'EMPLOYEE')")
public class LocationController {
    private final LocationService locationService;
    private final TrainingCenterRepository trainingCenterRepository;

    public LocationController(LocationService locationService, TrainingCenterRepository trainingCenterRepository) {
        this.locationService = locationService;
        this.trainingCenterRepository = trainingCenterRepository;
    }

    @GetMapping
    public String show(Model model) {
        loadListWithSubList(model);
        return "location_control";
    }

    private void loadSubList(Model model){
        List<TrainingCenter> trainingCenters = StreamSupport.stream(trainingCenterRepository.findAll().spliterator(),
                false).toList();
        model.addAttribute("trainingCenters", trainingCenters);
    }

    private void loadListWithSubList(Model model){
        List<Location> locations = StreamSupport.stream(locationService.getAll().spliterator(),
                false).toList();
        model.addAttribute("locations", locations);

        loadSubList(model);
    }

    @GetMapping("/exact")
    public String exactSearch(@RequestParam(value = "query" ) String query, Model model){
        if (!query.isEmpty()){
            List<Location> locations = locationService.getResultExactSearch(query);

            loadSubList(model);

            model.addAttribute("locations", locations);

            return "location_control";
        } else return "redirect:/location";
    }

    @GetMapping("/imprecise")
    public String impreciseSearch(@RequestParam(value = "query" ) String query, Model model){
        if (!query.isEmpty()){
            List<Location> locations = locationService.getResultImpreciseSearch(query);

            loadSubList(model);

            model.addAttribute("locations", locations);

            return "location_control";
        } else return "redirect:/location";
    }

    @PostMapping
    public String add(@Valid @ModelAttribute(value = "selectedLocation") Location location,
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

        loadListWithSubList(model);
        return "location_control";
    }
}
