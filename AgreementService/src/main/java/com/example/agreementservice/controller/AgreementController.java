package com.example.agreementservice.controller;

import com.example.agreementservice.client.ProjectClient;
import com.example.agreementservice.client.UserClient;
import com.example.agreementservice.dto.AgreementDto;
import com.example.agreementservice.dto.AgreementResponse;
import com.example.agreementservice.dto.ProjectDto;
import com.example.agreementservice.dto.UserDto;
import com.example.agreementservice.service.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agreements")
@RequiredArgsConstructor
public class AgreementController {

    private final AgreementService agreementService;
    private final ProjectClient projectClient;
    private final UserClient userClient;

    @PostMapping
    public ResponseEntity<?> createAgreement(@RequestBody AgreementDto request) {
        UserDto user = userClient.getUserById(request.getUserId());
        if (user == null) {
            return ResponseEntity.badRequest().body("User with ID " + request.getUserId() + " not found");
        }

        ProjectDto project = projectClient.getProjectsById(request.getProjectId());
        if (project == null) {
            return ResponseEntity.badRequest().body("Project with ID " + request.getProjectId() + " not found");
        }

        AgreementDto createdAgreement = agreementService.createAgreement(request);

        AgreementResponse response = new AgreementResponse(createdAgreement, project, user);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public List<AgreementResponse> getAll() {
        return agreementService.getAllAgreements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgreementDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(agreementService.getAgreementById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AgreementDto>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(agreementService.getAgreementsByUserId(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        agreementService.deleteAgreement(id);
        return ResponseEntity.ok("Agreement " + id + " deleted");
    }
}
