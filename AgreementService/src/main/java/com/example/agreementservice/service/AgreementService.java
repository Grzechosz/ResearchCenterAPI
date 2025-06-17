package com.example.agreementservice.service;

import com.example.agreementservice.client.ProjectClient;
import com.example.agreementservice.client.UserClient;
import com.example.agreementservice.dto.AgreementDto;
import com.example.agreementservice.dto.AgreementResponse;
import com.example.agreementservice.dto.ProjectDto;
import com.example.agreementservice.dto.UserDto;
import com.example.agreementservice.exception.AgreementNotFoundException;
import com.example.agreementservice.models.Agreement;
import com.example.agreementservice.repo.AgreementRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AgreementService {

    private final AgreementRepo agreementRepo;
    private final ProjectClient projectClient;
    private final UserClient userClient;

    public AgreementDto createAgreement(AgreementDto agreementRequest) {
        Agreement agreement = Agreement.builder()
                .userId(agreementRequest.getUserId())
                .projectId(agreementRequest.getProjectId())
                .build();

        Agreement saved = agreementRepo.save(agreement);
        log.info("Agreement {} saved for user {}", saved.getAgreementId(), saved.getUserId());
        try {
            projectClient.addAgreementIdToProject(saved.getProjectId(), saved.getAgreementId());
        } catch (Exception e) {
            log.warn("Could not update project {} with agreement {}: {}", saved.getProjectId(), saved.getAgreementId(), e.getMessage());
        }
        return mapToDto(saved);
    }

    @Transactional(readOnly = true)
    public List<AgreementResponse> getAllAgreements() {
        List<Agreement> agreements = agreementRepo.findAll();
        List<AgreementResponse> responses = new ArrayList<>();

        for (Agreement agreement : agreements) {
            responses.add(mapToAgreementResponse(agreement));
        }

        return responses;
    }

    @Transactional(readOnly = true)
    public AgreementDto getAgreementById(Long id) {
        Agreement agreement = agreementRepo.findById(id)
                .orElseThrow(() -> new AgreementNotFoundException("Agreement with ID " + id + " not found"));
        return mapToDto(agreement);
    }

    @Transactional(readOnly = true)
    public List<AgreementDto> getAgreementsByUserId(Long userId) {
        List<Agreement> agreements = agreementRepo.findAllByUserId(userId);
        List<AgreementDto> responses = new ArrayList<>();

        for (Agreement agreement : agreements) {
            responses.add(mapToDto(agreement));
        }
        return responses;
    }

    public void deleteAgreement(Long id) {
        Agreement agreement = agreementRepo.findById(id)
                .orElseThrow(() -> new AgreementNotFoundException("Agreement with ID " + id + " not found"));
        agreementRepo.delete(agreement);
        log.info("Agreement {} deleted", id);
    }

    private AgreementDto mapToDto(Agreement agreement) {
        ProjectDto project = null;

        try {
            project = projectClient.getProjectsById(agreement.getProjectId());
        } catch (Exception e) {
            log.warn("Could not fetch project with id {}: {}", agreement.getProjectId(), e.getMessage());
        }

        return AgreementDto.builder()
                .agreementId(agreement.getAgreementId())
                .userId(agreement.getUserId())
                .projectId(agreement.getProjectId())
                .project(project)
                .build();
    }

    private AgreementResponse mapToAgreementResponse(Agreement agreement) {
        ProjectDto project = null;
        UserDto user = null;

        try {
            project = projectClient.getProjectsById(agreement.getProjectId());
        } catch (Exception e) {
            log.warn("Could not fetch project with id {}: {}", agreement.getProjectId(), e.getMessage());
        }

        try {
            user = userClient.getUserById(agreement.getUserId());
        } catch (Exception e) {
            log.warn("Could not fetch user with id {}: {}", agreement.getUserId(), e.getMessage());
        }

        return AgreementResponse.builder()
                .agreement(mapToDto(agreement))
                .project(project)
                .user(user)
                .build();
    }
}