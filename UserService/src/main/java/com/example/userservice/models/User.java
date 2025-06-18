package com.example.userservice.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(length = 45)
    private String name;

    @Column(length = 45)
    private String surname;

    @Column(length = 45)
    private String password;

    @Column(length = 9)
    private int phoneNumber;

    @Column(length = 45)
    private String mail;

    @Column(name = "address_id")
    private Long addressId;

    @ElementCollection
    @CollectionTable(name = "user_agreement_ids", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "agreement_id")
    private List<Long> agreementIds;
}
