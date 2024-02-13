package com.example.clinical_worktime.services;

import com.example.gs_ws.TalonRequest;
import com.example.gs_ws.TalonResponse;
import com.example.clinical_worktime.entities.TalonEntity;

import javax.xml.datatype.DatatypeConfigurationException;
import java.time.LocalDate;
import java.util.List;

public interface TalonService {

    List<TalonResponse> createTalon(TalonRequest talon) throws DatatypeConfigurationException;


    List<TalonEntity> getFreeTalonsByDoctorIdAndDate(LocalDate date, Long doctorId);

    TalonEntity fillTalonById(Long doctorId, Long patientId, Long talonId);

    TalonEntity getTalonById(Long id);

    List<TalonEntity> findAllTalonByPatientId(Long patientId);

}

