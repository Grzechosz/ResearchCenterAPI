package com.denert.app.rest.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class TestResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long resultsId;

    @Column
    private String resultsDesc;

    @OneToOne(mappedBy = "testResult", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonManagedReference(value = "test-result")
    private TestCommission testCommission;

    public long getResultsId() {
        return resultsId;
    }

    public void setResultsId(long resultsId) {
        this.resultsId = resultsId;
    }

    public String getResultsDesc() {
        return resultsDesc;
    }

    public void setResultsDesc(String resultsDesc) {
        this.resultsDesc = resultsDesc;
    }

    public TestCommission getTestCommission() {
        return testCommission;
    }

    public void setTestCommission(TestCommission testCommission) {
        this.testCommission = testCommission;
    }
}
