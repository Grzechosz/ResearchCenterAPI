package com.denert.app.rest.dto;

import com.denert.app.rest.models.Project;
import com.denert.app.rest.models.TestResults;
import com.denert.app.rest.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestCommissionResponse {
    private long commissionId;

    private User user;

    private Project project;

    private Date date;

    private String description;

    private TestResults testResult;
}
