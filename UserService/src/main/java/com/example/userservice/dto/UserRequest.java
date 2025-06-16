package com.example.userservice.dto;

import com.example.adressservice.models.Address;
import com.example.agreementservice.models.Agreement;
import com.example.projectservice.models.TestCommission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
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
