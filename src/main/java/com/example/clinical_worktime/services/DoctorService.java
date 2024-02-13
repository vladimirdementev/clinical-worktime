package com.example.clinical_worktime.services;

import com.example.clinical_worktime.entities.DoctorEntity;

import java.util.List;

public interface DoctorService {
    List<DoctorEntity> getAllDoctors();

    DoctorEntity getDoctorById(Long id);

}
