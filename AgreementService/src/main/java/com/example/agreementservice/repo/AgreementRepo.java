package com.example.agreementservice.repo;

import com.example.shared.models.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgreementRepo extends JpaRepository<Agreement, Long> {
}
