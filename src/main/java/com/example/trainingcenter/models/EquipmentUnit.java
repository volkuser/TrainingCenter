package com.example.trainingcenter.models;

/* import java.util.HashSet;
import java.util.Set; */

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class EquipmentUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotBlank(message = "value cannot be empty")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9-]+$", message = "only letters should be used in the value")
    private String inventoryNumber;

    @ManyToOne
    @JoinColumn(name = "availability_id")
    private Availability availability;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    /* @OneToMany
    @JoinColumn(name = "inspected_unit_id")
    private Set<InspectedUnit> inspectedUnits = new HashSet<>(); */

    public EquipmentUnit(String inventoryNumber, Availability availability, Location location, Equipment equipment) {
        this.inventoryNumber = inventoryNumber;
        this.availability = availability;
        this.location = location;
        this.equipment = equipment;
    }

    public EquipmentUnit() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }
}
