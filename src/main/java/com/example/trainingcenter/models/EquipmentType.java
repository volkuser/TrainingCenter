package com.example.trainingcenter.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class EquipmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotBlank(message = "value cannot be empty")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "only letters should be used in the value")
    private String name;

    public EquipmentType(String name) {
        this.name = name;
    }

    public EquipmentType() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
