package com.denert.app.rest.Repo;

import com.denert.app.rest.Models.Address;
import com.denert.app.rest.Models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdresRepo extends JpaRepository<Address, Long> {
}
