package com.example.trainingcenter.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Positive(message = "value cannot be negative or null")
    private int number;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_center_id")
    private TrainingCenter trainingCenter;

    public Location(int number, TrainingCenter trainingCenter) {
        this.number = number;
        this.trainingCenter = trainingCenter;
    }

    public Location() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public TrainingCenter getTrainingCenter() {
        return trainingCenter;
    }

    public void setTrainingCenter(TrainingCenter trainingCenter) {
        this.trainingCenter = trainingCenter;
    }
}
