package com.example.projectservice.repo;

import com.example.projectservice.models.TestResults;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepo extends JpaRepository<TestResults, Long> {
}
