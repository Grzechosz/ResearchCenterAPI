package com.example.projectservice.models;

import com.example.userservice.models.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestCommission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commissionId;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonBackReference(value = "user-commissions")
    private User user;

    @ManyToOne
    @JoinColumn(name = "projectId")
    @JsonBackReference(value = "project-commissions")
    private Project project;

    @Column
    private Date date;

    @Column(length = 250)
    private String description;

    @OneToOne
    @MapsId
    @JoinColumn(name = "resultsId")
    @JsonBackReference(value = "test-result")
    private TestResults testResult;
}
