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
    public List<ProjectResponse> getProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping(value = "/{id}")
    public ProjectResponse getProject(@PathVariable long id) {
        return projectService.getProjectById(id);
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> saveProject(@RequestBody ProjectRequest projectRequest) {
        ProjectResponse savedProject = projectService.createProject(projectRequest);
        return ResponseEntity.ok(savedProject);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable long id, @RequestBody ProjectRequest projectRequest) {
        ProjectResponse updatedProject = projectService.updateProject(projectRequest, id);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping(value =  "/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Deleted project with id: " + id);
    }
}
