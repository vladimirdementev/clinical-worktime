package com.example.testprojectforktelabs.repository;

import com.example.testprojectforktelabs.entities.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

}
