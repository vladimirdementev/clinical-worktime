package com.example.testprojectforktelabs.services;

import com.example.testprojectforktelabs.entities.PatientEntity;
import org.springframework.stereotype.Service;

@Service
public interface PatientsService {

    PatientEntity getPatientById(Long id);

}
