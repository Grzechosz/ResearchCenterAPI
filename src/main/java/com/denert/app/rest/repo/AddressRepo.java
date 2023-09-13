package com.denert.app.rest.repo;

import com.denert.app.rest.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
