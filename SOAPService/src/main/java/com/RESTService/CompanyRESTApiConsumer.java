package com.RESTService;

import com.RESTService.Object.HelloObject;
import https.trainbooking_fr.train_booking_soap_service.BookTicketRequest;
import https.trainbooking_fr.train_booking_soap_service.SearchRequest;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CompanyRESTApiConsumer {

    private static CompanyRESTApiConsumer instance;
    private final String url = System.getenv("REST_ENDPOINT");

    public static CompanyRESTApiConsumer getInstance() {
        if (instance == null) {
            instance = new CompanyRESTApiConsumer();
        }
        return instance;
    }


    public String helloWorld(String name) {
        HelloObject hello = new RestTemplate().getForObject(url + "tests/hello/" + name, HelloObject.class);
        assert hello != null;
        return hello.getHelloText();
    }

    public String searchTrain(SearchRequest request) {
        String requestUrl = url + "search/train/";
        if (request.getFrom() != null && !request.getFrom().isEmpty()) {
            requestUrl += "FROM/" + request.getFrom() + "/";
            if (request.getTo() != null && !request.getTo().isEmpty()) {
                requestUrl += "TO/" + request.getTo() + "/";
                if (request.getDate() != null && !request.getDate().isEmpty()) {
                    requestUrl += "ON/" + request.getDate() + "/";
                    if (request.getClazz() != null && !request.getClazz().isEmpty()) {
                        requestUrl += "CLASS/" + request.getClazz() + "/";
                        if (request.getMinseats() != -1) {
                            requestUrl += "MINSEATS/" + request.getMinseats() + "/";
                        }
                    }
                }
            }
        }

        return new RestTemplate().getForObject(requestUrl, String.class);
    }

    public String bookTrain(BookTicketRequest request) {
        String requestUrl = url + "booking/" + request.getIdTrain() + "/CLASS/" + request.getClazz() + "/SEATS/" + request.getNBSeats();
        HttpEntity<BookTicketRequest> requestEntity = new HttpEntity<>(request);
        return new RestTemplate().exchange(requestUrl, HttpMethod.PUT, requestEntity, String.class).getBody();
    }
}