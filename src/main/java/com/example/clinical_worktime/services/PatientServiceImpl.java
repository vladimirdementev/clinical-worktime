package com.example.clinical_worktime.services;

import com.example.clinical_worktime.entities.PatientEntity;
import com.example.clinical_worktime.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientsService{

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public PatientEntity getPatientById(Long id) {
        return patientRepository.findById(id).orElseThrow();
    }
}
