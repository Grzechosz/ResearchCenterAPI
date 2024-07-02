package com.denert.app.rest.dto;

import com.denert.app.rest.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    private Long addressId;

    private Set<User> users = new HashSet<>();

    private String city;

    private String streetName;

    private String houseNumber;

    private int postcode;

    private int flatNumber;

}
