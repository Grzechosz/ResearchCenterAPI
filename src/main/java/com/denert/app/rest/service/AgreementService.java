package com.denert.app.rest.service;

import com.denert.app.rest.dto.AgreementRequest;
import com.denert.app.rest.dto.AgreementResponse;
import com.denert.app.rest.exception.AgreementNotFoundException;
import com.denert.app.rest.models.Agreement;
import com.denert.app.rest.repo.AgreementRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AgreementService {
    private final AgreementRepo agreementRepo;

    public AgreementResponse createAgreement(AgreementRequest agreementRequest){
        final Agreement agreement = Agreement.builder()
                .user(agreementRequest.getUser())
                .project(agreementRequest.getProject())
                .build();
        AgreementResponse agreementResponse = mapToAgreementResponse(agreementRepo.save(agreement));
        log.info("Agreement {} is saved", agreement.getAgreementId());

        return agreementResponse;
    }

    public List<AgreementResponse> getAllAgreements(){
        List<Agreement> agreements = agreementRepo.findAll();

        return agreements.stream().map(agreement -> mapToAgreementResponse(agreement)).toList();
    }

    public AgreementResponse getAgreementById(long id){
        return agreementRepo.findById(id)
                .map(this::mapToAgreementResponse)
                .orElseThrow(() -> new AgreementNotFoundException("Agreement with ID " + id + " not found."));
    }

    public AgreementResponse updateAgreement(AgreementRequest agreementRequest, long id) {
        return agreementRepo.findById(id)
                .map(existingAgreement -> {
                    existingAgreement.setProject(agreementRequest.getProject());
                    existingAgreement.setUser(agreementRequest.getUser());

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
                .user(agreement.getUser())
                .project(agreement.getProject())
                .build();
    }

}
