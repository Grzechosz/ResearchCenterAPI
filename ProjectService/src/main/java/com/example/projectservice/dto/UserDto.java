package com.example.projectservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private long userId;
    private String name;
    private String surname;
    private int phoneNumber;
    private String password;
    private String mail;
    private Long addressId;
}
