package com.example.clinical_worktime.services;

import com.example.clinical_worktime.entities.PatientEntity;
import org.springframework.stereotype.Service;

@Service
public interface PatientsService {

    PatientEntity getPatientById(Long id);

}
