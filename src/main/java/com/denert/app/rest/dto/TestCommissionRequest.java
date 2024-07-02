package com.denert.app.rest.dto;

import com.denert.app.rest.models.Project;
import com.denert.app.rest.models.TestResults;
import com.denert.app.rest.models.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestCommissionRequest {

    private User user;

    private Project project;

    private Date date;

    private String description;

    private TestResults testResult;
}
