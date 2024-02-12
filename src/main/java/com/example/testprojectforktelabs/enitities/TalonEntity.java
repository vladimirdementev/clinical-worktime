package com.example.testprojectforktelabs.enitities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="talons")
@Getter
@Setter
public class TalonEntity {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "serial")
    private Long id;
    @ManyToOne
    @JoinColumn(name="doctor_id")
    private DoctorEntity doctor;
    @ManyToOne
    @JoinColumn(name="patient_id")
    @Nullable
    private PatientEntity patient;
    private LocalTime startTime;
    private LocalDate date;
    @Transient
    private Duration duration;


    public TalonEntity() {
    }

    public TalonEntity(LocalTime startTime, LocalDate date, DoctorEntity doctor) {
        this.startTime = startTime;
        this.date = date;
        this.doctor = doctor;
    }
}
