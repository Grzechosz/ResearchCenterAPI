package com.example.agreementservice.client;

import com.example.agreementservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "project-service", url = "http://localhost:9004")
public interface ProjectClient {

    @GetMapping("/api/projects/agreement/{agreementId}")
    List<UserDto> getProjectsByAgreementId(@PathVariable Long agreementId);
}
