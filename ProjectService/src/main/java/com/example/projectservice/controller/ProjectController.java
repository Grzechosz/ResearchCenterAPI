package com.example.projectservice.controller;

import com.example.projectservice.dto.ProjectRequest;
import com.example.projectservice.dto.ProjectResponse;
import com.example.projectservice.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public List<ProjectResponse> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PutMapping("/{projectId}/add-agreement/{agreementId}")
    public ResponseEntity<Void> addAgreementToProject(
            @PathVariable Long projectId,
            @PathVariable Long agreementId
    ) {
        projectService.addAgreementId(projectId, agreementId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ProjectRequest getProject(@PathVariable long id) {
        return projectService.getProjectById(id);
    }

    @PostMapping
    public ResponseEntity<ProjectRequest> saveProject(@RequestBody ProjectRequest projectRequest) {
        ProjectRequest savedProject = projectService.createProject(projectRequest);
        return ResponseEntity.ok(savedProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectRequest> updateProject(@PathVariable long id, @RequestBody ProjectRequest request) {
        return ResponseEntity.ok(projectService.updateProject(request, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Deleted project with ID: " + id);
    }
}
