package com.denert.app.rest.repo;

import com.denert.app.rest.models.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgreementRepo extends JpaRepository<Agreement, Long> {
}
