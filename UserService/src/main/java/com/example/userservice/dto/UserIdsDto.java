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
public class UserIdsDto {
        private long userId;
        private String name;
        private String surname;
        private int phoneNumber;
        private String mail;
        private AddressDto address;
    }
