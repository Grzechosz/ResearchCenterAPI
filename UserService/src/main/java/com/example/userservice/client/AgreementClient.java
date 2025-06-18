package com.example.userservice.client;

import com.example.userservice.config.FeignConfig;
import com.example.userservice.dto.AgreementDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "agreement-service",
        url = "http://agreement-app:9001",
        configuration = FeignConfig.class
)
public interface AgreementClient {
    @GetMapping("/api/agreements/user/{id}")
    List<AgreementDto> getAgreementsByUserId(@PathVariable Long id);
}
