package com.example.testprojectforktelabs.repository;

import com.example.testprojectforktelabs.entities.TalonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TalonRepository extends JpaRepository<TalonEntity, Long> {

    Optional<TalonEntity> findByDateAndStartTimeAndDoctorId(LocalDate date, LocalTime startTime, Long doctorId);

    List<TalonEntity> findAllByDateAndDoctorIdAndPatientIdIsNull(LocalDate date, Long doctorId);

    List<TalonEntity> findAllByPatientId(Long patientId);

}
