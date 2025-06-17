package com.example.agreementservice.service;

import com.example.agreementservice.dto.AgreementResponse;
import com.example.agreementservice.exception.AgreementNotFoundException;
import com.example.agreementservice.models.Agreement;
import com.example.agreementservice.repo.AgreementRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AgreementService {
    private final AgreementRepo agreementRepo;

    public AgreementResponse createAgreement(){
        final Agreement agreement = Agreement.builder()
                .build();
        AgreementResponse agreementResponse = mapToAgreementResponse(agreementRepo.save(agreement));
        log.info("Agreement {} is saved", agreement.getAgreementId());

        return agreementResponse;
    }

    public List<AgreementResponse> getAllAgreements(){
        List<Agreement> agreements = agreementRepo.findAll();

        return agreements.stream().map(this::mapToAgreementResponse).toList();
    }

    public AgreementResponse getAgreementById(long id){
        return agreementRepo.findById(id)
                .map(this::mapToAgreementResponse)
                .orElseThrow(() -> new AgreementNotFoundException("Agreement with ID " + id + " not found."));
    }

    public List<AgreementResponse> getAgreementsByUserId(long userId) {
        List<Agreement> agreements = agreementRepo.findAllByUserId(userId);

        if (agreements.isEmpty()) {
            throw new AgreementNotFoundException("No agreements found for user ID " + userId);
        }

        return agreements.stream().map(this::mapToAgreementResponse).toList();
    }


    public AgreementResponse updateAgreement(long id) {
        return agreementRepo.findById(id)
                .map(existingAgreement -> {

                    Agreement updatedAgreement = agreementRepo.save(existingAgreement);
                    log.info("Agreement {} was updated", updatedAgreement.getAgreementId());
                    return mapToAgreementResponse(updatedAgreement);

                })  .orElseThrow(() -> new AgreementNotFoundException("Agreement with ID " + id + " not found"));
    }

    public void deleteAgreement(long id){
        Agreement agreementToDelete = agreementRepo.findById(id)
                .orElseThrow(() -> new AgreementNotFoundException("Agreement with ID " + id + " not found."));

        agreementRepo.delete(agreementToDelete);
        log.info("Agreement {} was deleted", id);
    }

    private AgreementResponse mapToAgreementResponse(Agreement agreement) {
        return AgreementResponse.builder()
                .agreementId(agreement.getAgreementId())
                .build();
    }

}
