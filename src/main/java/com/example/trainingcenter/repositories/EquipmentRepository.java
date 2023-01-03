package com.example.trainingcenter.repositories;

import com.example.trainingcenter.models.Equipment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EquipmentRepository extends CrudRepository<Equipment, Long> {
    List<Equipment> findByModel(String model);
}
