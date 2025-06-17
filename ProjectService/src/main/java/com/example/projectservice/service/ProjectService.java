package com.example.projectservice.service;

import com.example.projectservice.dto.ProjectDto;
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

    public ProjectDto createProject(ProjectDto projectRequest) {
        final Project project = Project.builder()
                .name(projectRequest.getName())
                .description(projectRequest.getDescription())
                .build();
        ProjectDto projectResponse = mapToProjectResponse(projectRepo.save(project));
        log.info("Project {} is saved", project.getProjectId());

        return projectResponse;
    }

    public List<ProjectDto> getAllProjects() {
        List<Project> projects = projectRepo.findAll();

        return projects.stream().map(this::mapToProjectResponse).toList();
    }

    public ProjectDto getProjectById(long id) {
        return projectRepo.findById(id)
                .map(this::mapToProjectResponse)
                .orElseThrow(() -> new ProjectNotFoundException("Project with ID " + id + " not found."));
    }

    public ProjectDto updateProject(ProjectDto projectRequest, long id) {
        return projectRepo.findById(id)
                .map(existingProject -> {
                    existingProject.setName(projectRequest.getName());
                    existingProject.setDescription(projectRequest.getDescription());

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

    private ProjectDto mapToProjectResponse(Project project) {
        return ProjectDto.builder()
                .projectId(project.getProjectId())
                .name(project.getName())
                .description(project.getDescription())
                .build();
    }
}
