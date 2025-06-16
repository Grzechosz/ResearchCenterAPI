package com.example.projectservice.models;

import com.example.agreementservice.models.Agreement;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long projectId;

    @Column(length = 45)
    private String name;

    @Column(length = 250)
    private String description;

    @OneToMany(mappedBy = "project")
    @JsonManagedReference(value = "project-agreements")
    private Set<Agreement> usersAgreements;

    @OneToMany(mappedBy = "project")
    @JsonManagedReference(value = "project-commissions")
    private Set<TestCommission> usersCommission;
}
