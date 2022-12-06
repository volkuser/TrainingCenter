package com.example.trainingcenter.repositories;

import com.example.trainingcenter.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findBySurname(String surname);
    List<Employee> findByName(String name);
}
