package com.example.trainingcenter.repositories;

import com.example.trainingcenter.models.Employee;
import com.example.trainingcenter.models.User;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findBySurname(String surname);
    List<Employee> findByName(String name);
    List<Employee> findByUser(User user);
}
