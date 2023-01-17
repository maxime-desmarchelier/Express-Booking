package com.SOAPService.Endpoint;

import com.RESTService.CompanyRESTApiConsumer;
import com.google.gson.Gson;
import https.trainbooking_fr.train_booking_soap_service.SearchRequest;
import https.trainbooking_fr.train_booking_soap_service.SearchResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Arrays;

@Endpoint
public class SearchEndpoint {

    private static final String NAMESPACE_URI = "https://trainbooking.fr/train-booking-soap-service";
    private final CompanyRESTApiConsumer consumingRestApplication;

    public SearchEndpoint() {
        consumingRestApplication = CompanyRESTApiConsumer.getInstance();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "SearchRequest")
    @ResponsePayload
    public SearchResponse search(@RequestPayload SearchRequest request) {

        String jsonData = consumingRestApplication.searchTrain(request);

        SearchResponse.Train[] trains = new Gson().fromJson(jsonData, SearchResponse.Train[].class);
        SearchResponse searchResponse = new SearchResponse();
        searchResponse.getTrain().addAll(Arrays.asList(trains));

        return searchResponse;
    }
}