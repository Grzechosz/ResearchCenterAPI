package com.denert.app.rest.Models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
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

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Agreement> getUsersAgreements() {
        return usersAgreements;
    }

    public void setUsersAgreements(Set<Agreement> usersAgreements) {
        this.usersAgreements = usersAgreements;
    }

    public Set<TestCommission> getUsersCommission() {
        return usersCommission;
    }

    public void setUsersCommission(Set<TestCommission> usersCommission) {
        this.usersCommission = usersCommission;
    }
}
