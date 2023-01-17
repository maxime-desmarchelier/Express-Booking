package com.SOAPService;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "tests")
    public DefaultWsdl11Definition testsWsdlDefinition(XsdSchema testsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("TestsPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("https://trainbooking.fr/train-booking-soap-service");
        wsdl11Definition.setSchema(testsSchema);
        return wsdl11Definition;
    }

    @Bean(name = "auth")
    public DefaultWsdl11Definition authWsdlDefinition(XsdSchema authSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("AuthPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("https://trainbooking.fr/train-booking-soap-service");
        wsdl11Definition.setSchema(authSchema);
        return wsdl11Definition;
    }

    @Bean(name = "train")
    public DefaultWsdl11Definition searchWsdlDefinition(XsdSchema trainSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("TrainPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("https://trainbooking.fr/train-booking-soap-service");
        wsdl11Definition.setSchema(trainSchema);
        return wsdl11Definition;
    }

    @Bean(name = "booking")
    public DefaultWsdl11Definition bookingWsdlDefinition(XsdSchema bookingSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("TrainPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("https://trainbooking.fr/train-booking-soap-service");
        wsdl11Definition.setSchema(bookingSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema testsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("tests.xsd"));
    }

    @Bean
    public XsdSchema authSchema() {
        return new SimpleXsdSchema(new ClassPathResource("auth.xsd"));
    }

    @Bean
    public XsdSchema trainSchema() {
        return new SimpleXsdSchema(new ClassPathResource("train.xsd"));
    }

    @Bean
    public XsdSchema bookingSchema() {
        return new SimpleXsdSchema(new ClassPathResource("book.xsd"));
    }

}
