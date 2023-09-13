package com.denert.app.rest.dto;

import com.denert.app.rest.models.TestCommission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestResultsRequest {
    private String resultsDesc;
    private TestCommission testCommission;
}
