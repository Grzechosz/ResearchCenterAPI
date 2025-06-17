package com.example.agreementservice.controller;

import com.example.agreementservice.dto.AgreementResponse;
import com.example.agreementservice.service.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agreement")
@RequiredArgsConstructor
public class AgreementController {

    private final AgreementService agreementService;

    @GetMapping
    public List<AgreementResponse> getAgreements() {
        return agreementService.getAllAgreements();
    }

    @GetMapping(value = "/{id}")
    public AgreementResponse getAgreement(@PathVariable long id) {
        return agreementService.getAgreementById(id);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AgreementResponse>> getAgreementsByUserId(@PathVariable long userId) {
        return ResponseEntity.ok(agreementService.getAgreementsByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<AgreementResponse> saveAgreement(@RequestBody AgreementResponse agreementRequest) {
        AgreementResponse savedAgreement = agreementService.createAgreement();
        return ResponseEntity.ok(savedAgreement);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AgreementResponse> updateAgreement(@PathVariable long id, @RequestBody AgreementResponse agreementRequest) {
        AgreementResponse updatedAgreement = agreementService.updateAgreement(id);
        return ResponseEntity.ok(updatedAgreement);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAgreement(@PathVariable long id) {
        agreementService.deleteAgreement(id);
        return ResponseEntity.ok("Deleted agreement with id: " + id);
    }
}