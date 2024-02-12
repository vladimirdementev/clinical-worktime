package com.example.testprojectforktelabs.services;

import com.example.gs_ws.TalonRequest;
import com.example.gs_ws.TalonResponse;
import com.example.testprojectforktelabs.exceptions.DuplicateTalonException;
import com.example.testprojectforktelabs.exceptions.NotFoundEntityException;
import com.example.testprojectforktelabs.utils.MappingTalonUtils;
import com.example.testprojectforktelabs.entities.DoctorEntity;
import com.example.testprojectforktelabs.entities.TalonEntity;
import com.example.testprojectforktelabs.repository.TalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TalonServiceImpl implements TalonService {

    private final TalonRepository talonRepository;
    private final DoctorService doctorService;
    private final PatientsService patientsService;

    @Autowired
    public TalonServiceImpl(TalonRepository talonRepository, DoctorService doctorService, PatientsService patientsService) {
        this.talonRepository = talonRepository;
        this.doctorService = doctorService;
        this.patientsService = patientsService;
    }

    @Override
    public List<TalonResponse> createTalon(TalonRequest talon) throws DatatypeConfigurationException {
        List<DoctorEntity> doctors = doctorService.getAllDoctors();
        if (doctors.isEmpty()) {
            throw new NotFoundEntityException("Список докторов пуст. Для создания расписания должен присутствовать хотя бы один доктор.");
        }
        List<TalonResponse> result = new ArrayList<>();
        for (DoctorEntity doctor : doctors) {
            createAndSaveTalonsForDoctor(talon, doctor, result);
        }
        return result;
    }

    private void createAndSaveTalonsForDoctor(TalonRequest talon, DoctorEntity doctor, List<TalonResponse> result) throws DatatypeConfigurationException {
        for (int i = 0; i < talon.getQuantity().intValue(); i++) {
            TalonEntity createdTalon = MappingTalonUtils.mapTalonRequestToTalonEntity(talon);
            createdTalon.setStartTime(createdTalon.getStartTime().plus(createdTalon.getDuration().multipliedBy(i)));
            createdTalon.setDoctor(doctor);
            checkForDuplicateTalon(createdTalon);
            talonRepository.save(createdTalon);
            result.add(MappingTalonUtils.mapTalonEntityToTalonResponse(createdTalon));
        }
    }

    private void checkForDuplicateTalon(TalonEntity createdTalon) {
        Optional<TalonEntity> existingTalon = talonRepository.findByDateAndStartTimeAndDoctorId(
                createdTalon.getDate(), createdTalon.getStartTime(), createdTalon.getDoctor().getId());
        if (existingTalon.isPresent()) {
            throw new DuplicateTalonException("Талон на " + createdTalon.getDate() + " к " +
                    createdTalon.getDoctor().getFullName() + " в " + createdTalon.getStartTime() + " уже существует.");
        }
    }

    @Override
    public List<TalonEntity> getFreeTalonsByDoctorIdAndDate(LocalDate date, Long doctorId) {
        return talonRepository.findAllByDateAndDoctorIdAndPatientIdIsNull(date,doctorId);
    }

    @Override
    public TalonEntity fillTalonById(Long doctorId, Long patientId, Long talonId) {
        TalonEntity talon = getTalonById(talonId);
        if (doctorId != null) {
            talon.setDoctor(doctorService.getDoctorById(doctorId));
        }
        talon.setPatient(patientsService.getPatientById(patientId));
        return talonRepository.save(talon);
    }

    @Override
    public TalonEntity getTalonById(Long id) {
        return talonRepository.findById(id).orElseThrow();
    }

    @Override
    public List<TalonEntity> findAllTalonByPatientId(Long patientId) {
        return  talonRepository.findAllByPatientId(patientId);
    }


}
