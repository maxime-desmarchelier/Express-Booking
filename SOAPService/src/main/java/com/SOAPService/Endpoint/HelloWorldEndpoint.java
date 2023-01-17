package com.SOAPService.Endpoint;

import com.RESTService.CompanyRESTApiConsumer;
import https.trainbooking_fr.train_booking_soap_service.GetHelloWorldRequest;
import https.trainbooking_fr.train_booking_soap_service.GetHelloWorldResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class HelloWorldEndpoint {
    private static final String NAMESPACE_URI = "https://trainbooking.fr/train-booking-soap-service";

    CompanyRESTApiConsumer consumingRestApplication;

    @Autowired
    public HelloWorldEndpoint() {
        consumingRestApplication = new CompanyRESTApiConsumer();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getHelloWorldRequest")
    @ResponsePayload
    public GetHelloWorldResponse getHelloWorld(@RequestPayload GetHelloWorldRequest request) {
        GetHelloWorldResponse response = new GetHelloWorldResponse();
        response.setHello(consumingRestApplication.helloWorld(request.getName()));
        return response;
    }
}
