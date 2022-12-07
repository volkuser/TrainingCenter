package com.example.trainingcenter.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class InventoryCommission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotBlank(message = "value cannot be empty")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9]+$", message = "pattern for value - \"a-zA-Zа-яА-Я0-9\"")
    private String number;
    @NotNull(message = "value cannot be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @PastOrPresent(message = "date must be in past or present")
    private Date eventDate;
    @Nullable
    @Transient
    private String eventDateAsString;

    public InventoryCommission(String number, Date eventDate) {
        this.number = number;
        this.eventDate = eventDate;
    }

    public InventoryCommission() { }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @Nullable
    public String getEventDateAsString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(eventDate);
    }
}
