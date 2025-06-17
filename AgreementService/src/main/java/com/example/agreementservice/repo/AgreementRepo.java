package com.example.agreementservice.repo;

import com.example.agreementservice.models.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgreementRepo extends JpaRepository<Agreement, Long> {
    List<Agreement> findAllByUserId(Long userId);
}