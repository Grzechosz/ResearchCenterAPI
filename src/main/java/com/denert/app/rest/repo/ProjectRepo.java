package com.denert.app.rest.repo;

import com.denert.app.rest.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project, Long> {
}
