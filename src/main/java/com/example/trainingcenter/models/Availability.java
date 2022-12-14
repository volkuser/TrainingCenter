package com.example.trainingcenter.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotBlank(message = "value cannot be empty")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "only letters should be used in the value")
    private String name;

    public Availability(String name) {
        this.name = name;
    }

    public Availability() {

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
