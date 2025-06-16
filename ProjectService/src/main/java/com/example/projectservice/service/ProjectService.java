package com.example.projectservice.service;

import com.example.projectservice.dto.ProjectRequest;
import com.example.projectservice.dto.ProjectResponse;
import com.example.projectservice.exception.ProjectNotFoundException;
import com.example.projectservice.models.Project;
import com.example.projectservice.repo.ProjectRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepo projectRepo;

    public ProjectResponse createProject(ProjectRequest projectRequest) {
        final Project project = Project.builder()
                .name(projectRequest.getName())
                .description(projectRequest.getDescription())
                .usersAgreements(projectRequest.getUsersAgreements())
                .usersCommission(projectRequest.getUsersCommission())
                .build();
        ProjectResponse projectResponse = mapToProjectResponse(projectRepo.save(project));
        log.info("Project {} is saved", project.getProjectId());

        return projectResponse;
    }

    public List<ProjectResponse> getAllProjects() {
        List<Project> projects = projectRepo.findAll();

        return projects.stream().map(this::mapToProjectResponse).toList();
    }

    public ProjectResponse getProjectById(long id) {
        return projectRepo.findById(id)
                .map(this::mapToProjectResponse)
                .orElseThrow(() -> new ProjectNotFoundException("Project with ID " + id + " not found."));
    }

    public ProjectResponse updateProject(ProjectRequest projectRequest, long id) {
        return projectRepo.findById(id)
                .map(existingProject -> {
                    existingProject.setName(projectRequest.getName());
                    existingProject.setDescription(projectRequest.getDescription());
                    existingProject.setUsersAgreements(projectRequest.getUsersAgreements());
                    existingProject.setUsersCommission(projectRequest.getUsersCommission());

                    Project updatedProject = projectRepo.save(existingProject);
                    log.info("Project {} was updated", updatedProject.getProjectId());
                    return mapToProjectResponse(updatedProject);

                }) .orElseThrow(() -> new ProjectNotFoundException("Project with ID " + id + " not found"));
    }

    public void deleteProject(long id) {
        Project projectToDelete = projectRepo.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Project with ID " + id + " not found."));

        projectRepo.delete(projectToDelete);
        log.info("Project {} was deleted", id);
    }

    private ProjectResponse mapToProjectResponse(Project project) {
        return ProjectResponse.builder()
                .projectId(project.getProjectId())
                .name(project.getName())
                .description(project.getDescription())
                .usersAgreements(project.getUsersAgreements())
                .usersCommission(project.getUsersCommission())
                .build();
    }
}
