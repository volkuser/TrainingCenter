package com.example.trainingcenter.repositories;

import com.example.trainingcenter.models.Employee;
import com.example.trainingcenter.models.InspectedUnit;
import com.example.trainingcenter.models.InventoryCommission;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InspectedUnitRepository extends CrudRepository<InspectedUnit, Long> {
    List<InspectedUnit> findByEmployee(Employee employee);
    List<InspectedUnit> findByInventoryCommissionAndEmployee(InventoryCommission inventoryCommission, 
        Employee employee);
}
