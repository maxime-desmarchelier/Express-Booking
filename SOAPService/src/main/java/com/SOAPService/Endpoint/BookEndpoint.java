package com.SOAPService.Endpoint;

import com.Database.BookingController;
import com.RESTService.CompanyRESTApiConsumer;
import https.trainbooking_fr.train_booking_soap_service.BookTicketRequest;
import https.trainbooking_fr.train_booking_soap_service.BookTicketResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class BookEndpoint {
    private static final String NAMESPACE_URI = "https://trainbooking.fr/train-booking-soap-service";

    private final CompanyRESTApiConsumer consumingRestApplication;

    public BookEndpoint() {
        consumingRestApplication = new CompanyRESTApiConsumer();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "BookTicketRequest")
    @ResponsePayload
    public BookTicketResponse bookTicket(@RequestPayload BookTicketRequest request) {
        String company = request.getCompany();
        String idTrain = request.getIdTrain();
        int NBSeats = request.getNBSeats();

        BookTicketResponse response = new BookTicketResponse();
        BookingController bookController = new BookingController();

        consumingRestApplication.bookTrain(request);

        response.setSucceed(true);

        return response;
    }
}