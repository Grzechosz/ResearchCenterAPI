package com.example.agreementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.agreementservice.client")
public class AgreementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgreementServiceApplication.class, args);
    }

}
