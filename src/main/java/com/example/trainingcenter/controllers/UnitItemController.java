package com.example.trainingcenter.controllers;

import com.example.trainingcenter.models.EquipmentType;
import com.example.trainingcenter.models.Unit;
import com.example.trainingcenter.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/unit/more")
@PreAuthorize("hasAnyAuthority('ADMINISTRATOR')")
public class UnitItemController {
    @Autowired
    private UnitRepository unitRepository;

    @GetMapping("/{id}")
    public String more(@PathVariable("id") String id, Model model){
        getAndLoad(model, id);
        return "unit_item_control";
    }

    private void getAndLoad(Model model, String id){
        Unit unit = unitRepository.findById(Long.parseLong(id)).orElseThrow();
        model.addAttribute("selectedUnit", unit);
    }

    @PostMapping("/{id}")
    public String update(@Valid @ModelAttribute(value = "selectedUnit") Unit unit,
                         BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else unitRepository.save(unit);

        getAndLoad(model, unit.getId().toString());
        return "unit_item_control";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") String id, Model model){
        try{
            unitRepository.deleteById(Long.parseLong(id));
            return "redirect:/unit";
        } catch (Exception exception) {
            getAndLoad(model, id);
            return "redirect:/unit/more/{id}";
        }
    }
}
