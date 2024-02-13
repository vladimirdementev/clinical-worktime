package com.example.clinical_worktime.services;

import com.example.clinical_worktime.entities.DoctorEntity;
import com.example.clinical_worktime.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<DoctorEntity> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public DoctorEntity getDoctorById(Long id) {
        return doctorRepository.findById(id).orElseThrow();
    }

}
