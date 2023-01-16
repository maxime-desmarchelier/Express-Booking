package com.RESTService;

import com.RESTService.Object.HelloObject;
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
        HelloObject hello = new RestTemplate().getForObject(
                url + "tests/hello/" + name, HelloObject.class);
        assert hello != null;
        return hello.getHelloText();
    }

    public String searchTrain(String from, String to) {
        return new RestTemplate().getForObject(
                url + "search/train/FROM/" + from + "/TO/" + to, String.class);
    }

    public void setUrl(String url) {
        this.url = url;
    }
}