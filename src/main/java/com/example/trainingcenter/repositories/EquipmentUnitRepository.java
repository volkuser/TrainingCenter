package com.example.trainingcenter.repositories;

import com.example.trainingcenter.models.EquipmentUnit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentUnitRepository extends CrudRepository<EquipmentUnit, Long> {
}
