package com.denert.app.rest.repo;

import com.denert.app.rest.models.TestCommission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestCommissionRepo extends JpaRepository<TestCommission, Long> {
}
