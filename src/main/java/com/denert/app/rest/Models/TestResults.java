package com.denert.app.rest.Models;

import jakarta.persistence.*;

@Entity
public class TestResults {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long resultsId;

    @Column(length = 255)
    private String resultsDesc;

    @OneToOne(mappedBy = "testResult", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
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
}
