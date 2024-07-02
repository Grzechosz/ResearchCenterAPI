package com.denert.app.rest.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Agreement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agreementId;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonBackReference(value = "user-agreements")
    private User user;

    @ManyToOne
    @JoinColumn(name = "projectId")
    @JsonBackReference(value = "project-agreements")
    private Project project;


}
