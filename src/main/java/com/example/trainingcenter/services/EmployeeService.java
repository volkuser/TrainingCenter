package com.example.trainingcenter.services;

import com.example.trainingcenter.models.Employee;
import com.example.trainingcenter.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    public Iterable<Employee> getAll() { return employeeRepository.findAll(); }

    private List<Employee> fusion(List<Employee> first, List<Employee> second){
        List<Employee> fusion = first;

        boolean match = false;
        for (Employee employeeInSecond : second){
            for (Employee employeeInFirst : first) {
                if (employeeInFirst.equals(employeeInSecond)) {
                    match = true;
                    break;
                }
            }
            if (!match) {
                fusion.add(employeeInSecond);
                match = false;
            }
        }

        return fusion;
    }

    public List<Employee> getResultExactSearch(String query) {
        List<Employee> desiredBySurname = new ArrayList<>(), desiredByName = new ArrayList<>();
        try {
            desiredBySurname = employeeRepository.findBySurname(query);
        } catch (Exception ignored) { }
        try {
            desiredByName = employeeRepository.findByName(query);
        } catch (Exception ignored) { }

        return fusion(desiredBySurname, desiredByName);
    }

    public List<Employee> getResultImpreciseSearch(String query) {
        List<Employee> desiredBySurname = new ArrayList<>(), desiredByName = new ArrayList<>();
        for (Employee employee : employeeRepository.findAll())
            if (employee.getSurname().contains(query)) desiredBySurname.add(employee);
        try {
            // imprecise search for char is identical to exact search for char
            desiredByName = employeeRepository.findByName(query);
        } catch (Exception ignored) { }

        return fusion(desiredBySurname, desiredByName);
    }

    public void save(Employee employee){
        employeeRepository.save(employee);
    }

    public Employee getById(Long id){
        return employeeRepository.findById(id).orElseThrow();
    }
    public void deleteById(Long id) { employeeRepository.deleteById(id); }
}
