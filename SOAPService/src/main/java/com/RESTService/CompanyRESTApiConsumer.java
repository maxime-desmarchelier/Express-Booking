package com.RESTService;

import com.RESTService.Object.HelloObject;
import https.trainbooking_fr.train_booking_soap_service.SearchRequest;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CompanyRESTApiConsumer {

    private String url;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
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
                    requestUrl += "DATE/" + request.getDate() + "/";
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

    public void setUrl(String url) {
        this.url = url;
    }
}