package com.denert.app.rest.Models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class TestCommission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commissionId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "projectId")
    private Project project;

    @Column
    private Date date;

    @Column(length = 250)
    private String description;

    @OneToOne
    @MapsId
    @JoinColumn(name = "resultsId")
    private TestResults testResult;
}
