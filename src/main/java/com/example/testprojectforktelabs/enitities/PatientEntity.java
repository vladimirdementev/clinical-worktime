package com.example.testprojectforktelabs.enitities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "patients")
@Getter
@Setter
public class PatientEntity {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "serial")
    private Long id;
    private String fullName;
    private LocalDateTime birthdate;

    public PatientEntity() {
    }

    public PatientEntity(String fullName, LocalDateTime birthdate) {
        this.fullName = fullName;
        this.birthdate = birthdate;
    }
}
