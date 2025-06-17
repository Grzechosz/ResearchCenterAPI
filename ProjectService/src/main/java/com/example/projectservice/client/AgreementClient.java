package com.example.projectservice.client;

import com.example.projectservice.dto.AgreementDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "agreement-service", url = "http://localhost:9001")
public interface AgreementClient {

    @GetMapping("/api/agreements/{id}")
    AgreementDto getAgreementById(@PathVariable Long id);
}
