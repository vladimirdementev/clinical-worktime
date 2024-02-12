package com.example.testprojectforktelabs.services;

import com.example.testprojectforktelabs.entities.DoctorEntity;

import java.util.List;

public interface DoctorService {
    List<DoctorEntity> getAllDoctors();

    DoctorEntity getDoctorById(Long id);

}
