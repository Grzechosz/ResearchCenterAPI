package com.example.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long addressId;
    private String city;
    private String streetName;
    private String houseNumber;
    private int postcode;
    private int flatNumber;
    private Long userId;
}
