package com.axon;

import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AxonProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AxonProductServiceApplication.class, args);
        System.out.println("Running Successfully...@");
    }


//    @Autowired
//    public void configure(EventProcessingConfigurer configurer){
//        configurer.registerListenerInvocationErrorHandler(
//                "product",
//                configuration -> new ProductServiceEventsErrorHandler()
//        );
//    }
}
