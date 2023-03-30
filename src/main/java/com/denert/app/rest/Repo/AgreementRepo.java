package com.denert.app.rest.Repo;

import com.denert.app.rest.Models.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgreementRepo extends JpaRepository<Agreement, Long> {
}
