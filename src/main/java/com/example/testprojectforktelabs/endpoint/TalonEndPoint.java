package com.example.testprojectforktelabs.endpoint;

import com.example.gs_ws.*;
import com.example.testprojectforktelabs.services.TalonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.List;


@Endpoint
public class TalonEndPoint {
    public static final String NAMESPACE_URI = "http://MyTestProject.com/talons-ws";


    private TalonService talonService;

    public TalonEndPoint() {

    }

    @Autowired
    public TalonEndPoint(TalonService talonService) {
        this.talonService = talonService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createTalonRequest")
    @ResponsePayload
    public CreateTalonResponse createTalon(@RequestPayload CreateTalonRequest request) throws DatatypeConfigurationException {
        ObjectFactory factrory = new ObjectFactory();
        CreateTalonResponse response = factrory.createCreateTalonResponse();
        for (TalonRequest talonInfo : request.getTalonInfo()) {
            List<TalonResponse> responses = talonService.createTalon(talonInfo);
            for (TalonResponse talonResponse : responses) {
                response.getTalon().add(talonResponse);
            }
        };
        return response;

    }
}
