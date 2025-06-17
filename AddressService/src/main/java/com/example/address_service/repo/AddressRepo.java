package com.example.address_service.repo;

import com.example.address_service.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepo extends JpaRepository<Address, Long> {
    Optional<Address> findByUserId(Long userId);
}
