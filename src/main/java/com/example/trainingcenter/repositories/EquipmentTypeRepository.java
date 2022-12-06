package com.example.trainingcenter.repositories;

import com.example.trainingcenter.models.EquipmentType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentTypeRepository extends CrudRepository<EquipmentType, Long> {
}
