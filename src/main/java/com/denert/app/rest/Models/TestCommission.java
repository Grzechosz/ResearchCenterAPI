package com.denert.app.rest.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Entity
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

    public long getCommissionId() {
        return commissionId;
    }

    public void setCommissionId(long commissionId) {
        this.commissionId = commissionId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TestResults getTestResult() {
        return testResult;
    }

    public void setTestResult(TestResults testResult) {
        this.testResult = testResult;
    }
}
