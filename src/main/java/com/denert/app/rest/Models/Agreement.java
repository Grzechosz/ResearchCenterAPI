package com.denert.app.rest.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
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

    public Long getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(Long agreementId) {
        this.agreementId = agreementId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
