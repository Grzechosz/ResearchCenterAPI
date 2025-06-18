package com.example.address_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(length = 45)
    private String city;

    @Column(length = 45)
    private String streetName;

    @Column(length = 6)
    private String houseNumber;

    @Column(length = 5)
    private int postcode;

    @Column(length = 4)
    private int flatNumber;

    @Column(name = "user_id")
    private Long userId;

}
