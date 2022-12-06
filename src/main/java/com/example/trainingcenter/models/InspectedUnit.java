package com.example.trainingcenter.models;

import jakarta.persistence.*;

@Entity
public class InspectedUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipment_unit_id")
    private EquipmentUnit equipmentUnit;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_commission_id")
    private InventoryCommission inventoryCommission;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public InspectedUnit(EquipmentUnit equipmentUnit, InventoryCommission inventoryCommission, Employee employee) {
        this.equipmentUnit = equipmentUnit;
        this.inventoryCommission = inventoryCommission;
        this.employee = employee;
    }

    public InspectedUnit() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public EquipmentUnit getEquipmentUnit() {
        return equipmentUnit;
    }

    public void setEquipmentUnit(EquipmentUnit equipmentUnit) {
        this.equipmentUnit = equipmentUnit;
    }

    public InventoryCommission getInventoryCommission() {
        return inventoryCommission;
    }

    public void setInventoryCommission(InventoryCommission inventoryCommission) {
        this.inventoryCommission = inventoryCommission;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
