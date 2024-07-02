package com.denert.app.rest.repo;

import com.denert.app.rest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
