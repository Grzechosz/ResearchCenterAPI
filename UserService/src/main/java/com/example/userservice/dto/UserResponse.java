package com.example.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private List<AgreementDto> projectsAgreements;

    private AddressDto address;
}
