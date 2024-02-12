package com.example.testprojectforktelabs.services;

import com.example.testprojectforktelabs.enitities.PatientEntity;
import com.example.testprojectforktelabs.repository.PatientRepository;
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
