package com.denert.app.rest.Controller;

import com.denert.app.rest.Models.Project;
import com.denert.app.rest.Repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectRepo projectRepo;

    @GetMapping(value = "/projects")
    private List<Project> getProjects() {
        return projectRepo.findAll();
    }

    @GetMapping(value = "/projects/{id}")
    public Project getProject(@PathVariable long id) {
        return projectRepo.findById(id).get();
    }

    @PostMapping(value = "/projects")
    public ResponseEntity<Project> saveProject(@RequestBody Project project) {
        projectRepo.save(project);
        return ResponseEntity.ok(project);
    }

    @PutMapping(value = "/projects/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable long id, @RequestBody Project project) {
        Project updateProject = projectRepo.findById(id).get();

        updateProject.setDescription(project.getDescription());
        updateProject.setName(project.getName());
        updateProject.setUsersAgreements(project.getUsersAgreements());
        updateProject.setUsersCommission(project.getUsersCommission());

        projectRepo.save(updateProject);

        return ResponseEntity.ok(updateProject);
    }

    @DeleteMapping(value =  "/projects/{id}")
    public String deleteProject(@PathVariable long id) {
        Project deleteProject = projectRepo.findById(id).get();
        projectRepo.delete(deleteProject);
        return "Deleted project with id: "+ id;
    }
}
