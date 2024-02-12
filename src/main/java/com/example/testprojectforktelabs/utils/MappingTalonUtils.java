package com.example.testprojectforktelabs.utils;

import com.example.gs_ws.TalonRequest;
import com.example.gs_ws.TalonResponse;
import com.example.testprojectforktelabs.entities.TalonEntity;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.time.format.DateTimeFormatter;

@Service
public class MappingTalonUtils {

        private static XMLGregorianCalendar toXMLGregorianCalendar(String localDateOrTime) throws DatatypeConfigurationException {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(localDateOrTime);
        }

        public static TalonResponse mapTalonEntityToTalonResponse(TalonEntity talonEntity) throws DatatypeConfigurationException {
            TalonResponse talon = new TalonResponse();
            talon.setDate(toXMLGregorianCalendar(talonEntity.getDate().toString()));
            talon.setStartTime(toXMLGregorianCalendar(talonEntity.getStartTime().format(DateTimeFormatter.ISO_TIME)));
            talon.setDoctorId(BigInteger.valueOf(talonEntity.getDoctor().getId()));
            return talon;
        }

        public static TalonEntity mapTalonRequestToTalonEntity(TalonRequest talon) {
            TalonEntity talonEntity = new TalonEntity();
            talonEntity.setDate(talon.getDate().toGregorianCalendar().toZonedDateTime().toLocalDate());
            talonEntity.setStartTime(talon.getStartTime().toGregorianCalendar().toZonedDateTime().toLocalTime());
            talonEntity.setDuration(java.time.Duration.parse(talon.getDuration().toString()));
            return talonEntity;
        }


}
