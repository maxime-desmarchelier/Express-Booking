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
        String clazz = request.getClazz();
        String token = request.getToken();
        int NBSeats = request.getNBSeats();

        boolean success = false;

        if (AuthEndpoint.checkTokenValidity(token)) {
            BookingController bookController = new BookingController();
            bookController.createReservation(idTrain, clazz, NBSeats, 0, token);
            consumingRestApplication.bookTrain(request);
            success = true;
        }
        BookTicketResponse response = new BookTicketResponse();
        response.setSucceed(success);

        return response;
    }
}