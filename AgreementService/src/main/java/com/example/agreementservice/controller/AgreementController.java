package com.example.agreementservice.controller;

import com.example.agreementservice.service.AgreementService;
import com.example.shared.dto.AgreementRequest;
import com.example.shared.dto.AgreementResponse;
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

    @PostMapping
    public ResponseEntity<AgreementResponse> saveAgreement(@RequestBody AgreementRequest agreementRequest) {
        AgreementResponse savedAgreement = agreementService.createAgreement(agreementRequest);
        return ResponseEntity.ok(savedAgreement);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AgreementResponse> updateAgreement(@PathVariable long id, @RequestBody AgreementRequest agreementRequest) {
        AgreementResponse updatedAgreement = agreementService.updateAgreement(agreementRequest, id);
        return ResponseEntity.ok(updatedAgreement);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAgreement(@PathVariable long id) {
        agreementService.deleteAgreement(id);
        return ResponseEntity.ok("Deleted agreement with id: " + id);
    }
}