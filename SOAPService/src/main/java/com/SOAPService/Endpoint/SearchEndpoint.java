package com.SOAPService.Endpoint;

import com.RESTService.CompanyRESTApiConsumer;
import https.trainbooking_fr.train_booking_soap_service.SearchRequest;
import https.trainbooking_fr.train_booking_soap_service.SearchResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class SearchEndpoint {

    private static final String NAMESPACE_URI = "https://trainbooking.fr/train-booking-soap-service";
    private final CompanyRESTApiConsumer consumingRestApplication;

    public SearchEndpoint() {
        consumingRestApplication = new CompanyRESTApiConsumer();
        consumingRestApplication.setUrl("http://localhost:3000/");
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SearchRequest")
    @ResponsePayload
    public SearchResponse search(@RequestPayload SearchRequest request) {
        SearchResponse response = new SearchResponse();

        String from = request.getFrom();
        String to = request.getTo();

        response.setTrainList(consumingRestApplication.searchTrain(from, to));

        return response;
    }
}