package com.example.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest{
    private long userId;

    private String name;

    private String surname;

    private int phoneNumber;

    private String password;

    private String mail;

    private Set<Long> projectsAgreementsId;

    private Long addressId;
}
