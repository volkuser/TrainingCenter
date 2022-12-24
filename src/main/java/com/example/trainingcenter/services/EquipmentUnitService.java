package com.example.trainingcenter.services;

import com.example.trainingcenter.models.EquipmentUnit;
import com.example.trainingcenter.repositories.EquipmentUnitRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentUnitService {
    final
    EquipmentUnitRepository equipmentUnitRepository;

    public EquipmentUnitService(EquipmentUnitRepository equipmentUnitRepository) {
        this.equipmentUnitRepository = equipmentUnitRepository;
    }

    public Iterable<EquipmentUnit> getAll() { return equipmentUnitRepository.findAll(); }

    public List<EquipmentUnit> getResultExactSearch(String query) {
        return equipmentUnitRepository.findByInventoryNumber(query);
    }

    public List<EquipmentUnit> getResultImpreciseSearch(String query) {
        List<EquipmentUnit> desired = new ArrayList<>();

        for (EquipmentUnit equipmentUnit : equipmentUnitRepository.findAll())
            if (equipmentUnit.getInventoryNumber().contains(query)) desired.add(equipmentUnit);

        return desired;
    }

    public void save(EquipmentUnit equipmentUnit){
        equipmentUnitRepository.save(equipmentUnit);
    }

    public EquipmentUnit getById(Long id){
        return equipmentUnitRepository.findById(id).orElseThrow();
    }
    public void deleteById(Long id) { equipmentUnitRepository.deleteById(id); }
}
