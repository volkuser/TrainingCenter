package com.example.trainingcenter.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Pattern(regexp = "[0-9]{3}", message = "there should be three numbers")
    private String OKEICode;

    @NotBlank(message = "value cannot be empty")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я ]+$", message = "only letters should be used in the value")
    private String name;

    public Unit(String OKEICode, String name) {
        this.OKEICode = OKEICode;
        this.name = name;
    }

    public Unit() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getOKEICode() {
        return OKEICode;
    }

    public void setOKEICode(String OKEICode) {
        this.OKEICode = OKEICode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
