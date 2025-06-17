package com.example.projectservice.service;

import com.example.projectservice.client.AgreementClient;
import com.example.projectservice.dto.AgreementDto;
import com.example.projectservice.dto.ProjectRequest;
import com.example.projectservice.dto.ProjectResponse;
import com.example.projectservice.exception.ProjectNotFoundException;
import com.example.projectservice.models.Project;
import com.example.projectservice.repo.ProjectRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepo projectRepo;
    private final AgreementClient agreementClient;

    public void addAgreementId(Long projectId, Long agreementId) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found"));

        if (project.getAgreementIds() == null) {
            project.setAgreementIds(new HashSet<>());
        }

        project.getAgreementIds().add(agreementId);
        projectRepo.save(project);
        log.info("Added agreement {} to project {}", agreementId, projectId);
    }


    public ProjectRequest createProject(ProjectRequest projectRequest) {
        final Project project = Project.builder()
                .name(projectRequest.getName())
                .description(projectRequest.getDescription())
                .agreementIds(projectRequest.getAgreementIds())
                .build();

        Project saved = projectRepo.save(project);
        log.info("Project {} is saved", saved.getProjectId());

        return mapToProjectRequest(saved);
    }

    public List<ProjectResponse> getAllProjects() {
        return projectRepo.findAll().stream()
                .map(this::mapToProjectResponse)
                .toList();
    }

    public ProjectRequest getProjectById(long id) {
        return projectRepo.findById(id)
                .map(this::mapToProjectRequest)
                .orElseThrow(() -> new ProjectNotFoundException("Project with ID " + id + " not found."));
    }

    public ProjectRequest updateProject(ProjectRequest projectRequest, long id) {
        return projectRepo.findById(id)
                .map(existingProject -> {
                    existingProject.setName(projectRequest.getName());
                    existingProject.setDescription(projectRequest.getDescription());
                    existingProject.setAgreementIds(projectRequest.getAgreementIds());

                    Project updated = projectRepo.save(existingProject);
                    log.info("Project {} was updated", updated.getProjectId());
                    return mapToProjectRequest(updated);
                })
                .orElseThrow(() -> new ProjectNotFoundException("Project with ID " + id + " not found"));
    }

    public void deleteProject(long id) {
        Project projectToDelete = projectRepo.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project with ID " + id + " not found."));
        projectRepo.delete(projectToDelete);
        log.info("Project {} was deleted", id);
    }

    private ProjectRequest mapToProjectRequest(Project project) {
        return ProjectRequest.builder()
                .projectId(project.getProjectId())
                .name(project.getName())
                .description(project.getDescription())
                .agreementIds(project.getAgreementIds())
                .build();
    }

    private ProjectResponse mapToProjectResponse(Project project) {
        List<AgreementDto> agreements = new ArrayList<>();

        if (project.getAgreementIds() != null) {
            for (Long id : project.getAgreementIds()) {
                try {
                    AgreementDto agreement = agreementClient.getAgreementById(id);
                    agreements.add(agreement);
                } catch (Exception e) {
                    log.warn("Could not fetch agreement {}: {}", id, e.getMessage());
                }
            }
        }

        return ProjectResponse.builder()
                .projectId(project.getProjectId())
                .name(project.getName())
                .description(project.getDescription())
                .agreements(agreements)
                .build();
    }
}
