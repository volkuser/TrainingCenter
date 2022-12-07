package com.example.trainingcenter.services;

import com.example.trainingcenter.models.InspectedUnit;
import com.example.trainingcenter.repositories.InspectedUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InspectedUnitService {
    @Autowired
    private InspectedUnitRepository inspectedUnitRepository;
    @Autowired
    private EmployeeService employeeService;

    public Iterable<InspectedUnit> getAll() { return inspectedUnitRepository.findAll(); }

    public void save(InspectedUnit inspectedUnit){
        inspectedUnitRepository.save(inspectedUnit);
    }

    public List<InspectedUnit> getByLocationId(String employeeIdAsString) {
        return inspectedUnitRepository.findByEmployee(employeeService.getById(Long.parseLong(employeeIdAsString))); }

    public InspectedUnit getById(Long id){
        return inspectedUnitRepository.findById(id).orElseThrow();
    }
    public void deleteById(Long id) { inspectedUnitRepository.deleteById(id); }
}
