package com.example.trainingcenter.repositories;

import com.example.trainingcenter.models.EquipmentUnit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentUnitRepository extends CrudRepository<EquipmentUnit, Long> {
    List<EquipmentUnit> findByInventoryNumber(String inventoryNumber);
}
