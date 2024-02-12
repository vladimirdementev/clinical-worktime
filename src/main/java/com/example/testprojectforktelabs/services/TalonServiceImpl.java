package com.example.testprojectforktelabs.services;

import com.example.gs_ws.TalonRequest;
import com.example.gs_ws.TalonResponse;
import com.example.testprojectforktelabs.Exceptions.DuplicateTalonException;
import com.example.testprojectforktelabs.Exceptions.NotFoundEntityException;
import com.example.testprojectforktelabs.utils.MappingTalonUtils;
import com.example.testprojectforktelabs.enitities.DoctorEntity;
import com.example.testprojectforktelabs.enitities.TalonEntity;
import com.example.testprojectforktelabs.repository.TalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        /*для каждого доктора формируем заданное кол-во слотов*/
        for (DoctorEntity doctor : doctors) {
            for (int i = 0; i < talon.getQuantity().intValue(); i++) {
                TalonEntity createdTalon = MappingTalonUtils.mapTalonRequestToTalonEntity(talon);
                /*модифицируем время начала талона и привязываем доктора*/
                createdTalon.setStartTime(createdTalon.getStartTime().plus(createdTalon.getDuration().multipliedBy(i)));
                createdTalon.setDoctor(doctor);
                /*Если существует талон к текущему врачу на заданное время или в промежутке заданного времени выкидываем ошибку*/
                if (talonRepository.findByDateAndStartTimeAndDoctorId(createdTalon.getDate(), createdTalon.getStartTime(), createdTalon.getDoctor().getId()).isPresent()) {
                    throw new DuplicateTalonException("Талон на " + createdTalon.getDate().toString() + " к " + doctor.getFullName() + " в " + createdTalon.getStartTime().toString() + " уже существует.");
                }
                talonRepository.save(createdTalon);
                result.add(MappingTalonUtils.mapTalonEntityToTalonResponse(createdTalon));

            }
        }
        return result;
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
