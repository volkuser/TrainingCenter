package com.example.trainingcenter.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class TrainingCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotBlank(message = "value cannot be empty")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9- ]+$", message = "there should be no special characters")
    private String address;
}
