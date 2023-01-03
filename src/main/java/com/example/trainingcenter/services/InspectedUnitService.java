package com.example.trainingcenter.services;

import com.example.trainingcenter.models.Employee;
import com.example.trainingcenter.models.InspectedUnit;
import com.example.trainingcenter.models.InventoryCommission;
import com.example.trainingcenter.repositories.InspectedUnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InspectedUnitService {
    private final InspectedUnitRepository inspectedUnitRepository;
    private final EmployeeService employeeService;

    public InspectedUnitService(InspectedUnitRepository inspectedUnitRepository, EmployeeService employeeService) {
        this.inspectedUnitRepository = inspectedUnitRepository;
        this.employeeService = employeeService;
    }

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

    public List<InspectedUnit> getByEmployee(Employee employee){
        return inspectedUnitRepository.findByEmployee(employee);
    }

    public List<InspectedUnit> getByInventoryCommissionAndEmployee(InventoryCommission inventoryCommission,
        Employee employee) {
        return inspectedUnitRepository.findByInventoryCommissionAndEmployee(inventoryCommission, employee);
    }
}
