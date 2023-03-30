package com.denert.app.rest.Repo;

import com.denert.app.rest.Models.TestResults;
import com.denert.app.rest.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepo extends JpaRepository<TestResults, Long> {
}
