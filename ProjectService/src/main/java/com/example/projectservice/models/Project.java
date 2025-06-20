package com.example.projectservice.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long projectId;

    @Column(length = 45)
    private String name;

    @Column(length = 250)
    private String description;

    @ElementCollection
    @CollectionTable(name = "project_agreement_ids", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "agreement_id")
    private Set<Long> agreementIds;
}
