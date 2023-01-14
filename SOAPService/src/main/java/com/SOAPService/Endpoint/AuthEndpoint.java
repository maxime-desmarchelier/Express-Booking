package com.SOAPService.Endpoint;

import https.trainbooking_fr.train_booking_soap_service.AuthRequest;
import https.trainbooking_fr.train_booking_soap_service.AuthResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class AuthEndpoint {

    private static final String NAMESPACE_URI = "https://trainbooking.fr/train-booking-soap-service";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AuthRequest")
    @ResponsePayload
    public AuthResponse authenticate(@RequestPayload AuthRequest request) {
        String user = request.getUsername();
        String pass = request.getPassword();

        //userController.insertNewUser(user, pass);

        AuthResponse response = new AuthResponse();

        if (user.equals("admin") && pass.equals("admin")) {
            response.setAuthToken("123456");
        } else {
            response.setAuthToken("0");
        }


        return response;
    }
}