package com.RESTService.Object;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HelloObject {

    public String getHelloText() {
        return helloText;
    }

    public void setHelloText(String helloText) {
        this.helloText = helloText;
    }

    private String helloText;

    public HelloObject() {
    }

    @Override
    public String toString() {
        return "HelloObject{" + "name='" + helloText + '\'' + '}';
    }
}