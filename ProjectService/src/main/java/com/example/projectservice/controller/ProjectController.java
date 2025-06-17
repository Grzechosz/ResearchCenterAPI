package com.example.projectservice.controller;

import com.example.projectservice.dto.ProjectDto;
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
    public List<ProjectDto> getProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping(value = "/{id}")
    public ProjectDto getProject(@PathVariable long id) {
        return projectService.getProjectById(id);
    }

    @PostMapping
    public ResponseEntity<ProjectDto> saveProject(@RequestBody ProjectDto projectRequest) {
        ProjectDto savedProject = projectService.createProject(projectRequest);
        return ResponseEntity.ok(savedProject);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable long id, @RequestBody ProjectDto projectRequest) {
        ProjectDto updatedProject = projectService.updateProject(projectRequest, id);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping(value =  "/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Deleted project with id: " + id);
    }
}
