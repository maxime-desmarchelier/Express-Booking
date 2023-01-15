package com.SOAPService.Endpoint;

import com.Database.AuthController;
import https.trainbooking_fr.train_booking_soap_service.AuthCreateRequest;
import https.trainbooking_fr.train_booking_soap_service.AuthCreateResponse;
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

        AuthResponse response = new AuthResponse();

        AuthController authController = new AuthController();
        if (authController.userExists(user, pass)) {
            response.setAuthToken("123456789");
        } else {
            response.setAuthToken("0");
        }

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "AuthCreateRequest")
    @ResponsePayload
    public AuthCreateResponse createUser(@RequestPayload AuthCreateRequest request) {
        String user = request.getUsername();
        String pass = request.getPassword();

        AuthCreateResponse response = new AuthCreateResponse();

        AuthController authController = new AuthController();
        response.setSucceed(authController.createUser(user, pass));

        return response;
    }
}