package com.example.projectservice.dto;

import com.example.projectservice.models.TestCommission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestResultsResponse {

    private long resultsId;
    private String resultsDesc;
    private TestCommission testCommission;
}
