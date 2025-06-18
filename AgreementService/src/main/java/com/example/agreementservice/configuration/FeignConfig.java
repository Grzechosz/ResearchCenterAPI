package com.example.agreementservice.configuration;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            String username = "admin";
            String password = "admin";
            String base64Creds = Base64.getEncoder()
                    .encodeToString((username + ":" + password).getBytes());

            requestTemplate.header("Authorization", "Basic " + base64Creds);
        };
    }
}
