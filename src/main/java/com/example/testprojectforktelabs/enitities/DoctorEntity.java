package com.example.testprojectforktelabs.enitities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "doctors")
@Getter
@Setter
public class DoctorEntity {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "serial")
    private Long id;
    private String fullName;
    private String profession;


    public DoctorEntity() {
    }

    public DoctorEntity(String fullName, String profession) {
        this.fullName = fullName;
        this.profession = profession;
    }
}
