package com.example.trainingcenter.services;

import com.example.trainingcenter.models.Equipment;
import com.example.trainingcenter.repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired LocationService locationService;

    public Iterable<Equipment> getAll() { return equipmentRepository.findAll(); }

    public List<Equipment> getResultExactSearch(String query) {
        return equipmentRepository.findByModel(query);
    }

    public List<Equipment> getResultImpreciseSearch(String query) {
        List<Equipment> desired = new ArrayList<>();

        for (Equipment equipment : equipmentRepository.findAll())
            if (equipment.getModel().contains(query)) desired.add(equipment);

        return desired;
    }

    public void save(Equipment equipment){
        equipmentRepository.save(equipment);
    }

    public Equipment getById(Long id){
        return equipmentRepository.findById(id).orElseThrow();
    }
    public void deleteById(Long id) { equipmentRepository.deleteById(id); }
}
