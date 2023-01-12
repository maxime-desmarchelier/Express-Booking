package com.SOAPService;

import io.spring.guides.gs_producing_web_service.GetHelloWorldRequest;
import io.spring.guides.gs_producing_web_service.GetHelloWorldResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class HelloWorldEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    ConsumingRestApplication consumingRestApplication;

    @Autowired
    public HelloWorldEndpoint() {
        consumingRestApplication = new ConsumingRestApplication();
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getHelloWorldRequest")
    @ResponsePayload
    public GetHelloWorldResponse getHelloWorld(@RequestPayload GetHelloWorldRequest request) {
        GetHelloWorldResponse response = new GetHelloWorldResponse();
        response.setHello(consumingRestApplication.helloWorld(request.getName()));
        return response;
    }
}
