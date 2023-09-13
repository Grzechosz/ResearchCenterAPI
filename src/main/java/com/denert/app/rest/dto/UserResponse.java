package com.denert.app.rest.dto;

import com.denert.app.rest.models.Address;
import com.denert.app.rest.models.Agreement;
import com.denert.app.rest.models.TestCommission;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private long userId;

    private String name;

    private String surname;

    private int phoneNumber;

    private String password;

    private String mail;

    private int permissions;

    private Set<Agreement> projectsAgreements;

    private Set<TestCommission> projectsCommission;

    private Address address;
}
