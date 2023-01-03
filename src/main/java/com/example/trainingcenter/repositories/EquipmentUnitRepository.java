package com.example.trainingcenter.repositories;

import com.example.trainingcenter.models.EquipmentUnit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EquipmentUnitRepository extends CrudRepository<EquipmentUnit, Long> {
    List<EquipmentUnit> findByInventoryNumber(String inventoryNumber);
}
