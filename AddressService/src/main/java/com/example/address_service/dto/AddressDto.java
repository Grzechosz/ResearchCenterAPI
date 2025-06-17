package com.example.address_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private Long addressId;

    private Set<UserDto> users;

    private String city;

    private String streetName;

    private String houseNumber;

    private int postcode;

    private int flatNumber;

}
