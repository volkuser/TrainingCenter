package com.example.trainingcenter.repositories;

import com.example.trainingcenter.models.Employee;
import com.example.trainingcenter.models.InspectedUnit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InspectedUnitRepository extends CrudRepository<InspectedUnit, Long> {
    List<InspectedUnit> findByEmployee(Employee employee);
}
