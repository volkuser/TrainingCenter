package com.example.trainingcenter.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotBlank(message = "value cannot be empty")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я- ]+$", message = "only letters should be used in the value")
    private String surname, name;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private User user;

    public Employee() { }

    public Employee(String surname, String name, User user) {
        this.surname = surname;
        this.name = name;
        this.user = user;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
