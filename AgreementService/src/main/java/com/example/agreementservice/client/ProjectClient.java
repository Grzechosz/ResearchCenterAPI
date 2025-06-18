package com.example.agreementservice.client;

import com.example.agreementservice.configuration.FeignConfig;
import com.example.agreementservice.dto.ProjectDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@FeignClient(name = "project-service",
        url = "http://localhost:9002",
        configuration = FeignConfig.class)
public interface ProjectClient {

    @GetMapping("/api/projects/{id}")
    ProjectDto getProjectsById(@PathVariable Long id);

    @PutMapping("api/projects/{projectId}/add-agreement/{agreementId}")
    void addAgreementIdToProject(@PathVariable Long projectId, @PathVariable Long agreementId);

}
