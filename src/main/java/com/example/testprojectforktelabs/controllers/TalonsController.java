package com.example.testprojectforktelabs.controllers;

import com.example.testprojectforktelabs.entities.TalonEntity;
import com.example.testprojectforktelabs.services.TalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/talons")
public class TalonsController {

    private TalonService talonService;

    @Autowired
    public TalonsController(TalonService talonService) {
        this.talonService = talonService;
    }

    @PostMapping("/getFreeTalonsByDoctorAndDate/{doctorId}")
    public List<TalonEntity> getFreeTalonsByDoctorAndDate(@RequestParam LocalDate date, @PathVariable("doctorId") Long doctorId) {
        return talonService.getFreeTalonsByDoctorIdAndDate(date, doctorId);
    }

    @PostMapping("/fillTalonById/{id}")
    public TalonEntity fillTalonById(@RequestParam(required = false) Long doctorId, @RequestParam Long patientId, @PathVariable("id") Long talonId) {
        return talonService.fillTalonById(doctorId,patientId,talonId);
    }

    @GetMapping("/getTalonsByPatientId/{id}")
    public List<TalonEntity> getTalonsByPatientId(@PathVariable("id") Long patientId) {
        return talonService.findAllTalonByPatientId(patientId);
    }



}
