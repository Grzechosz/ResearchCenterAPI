package com.denert.app.rest.repo;

import com.denert.app.rest.models.TestResults;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepo extends JpaRepository<TestResults, Long> {
}
