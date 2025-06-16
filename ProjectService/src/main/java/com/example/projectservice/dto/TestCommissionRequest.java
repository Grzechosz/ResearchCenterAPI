package com.example.projectservice.dto;

import com.example.projectservice.models.Project;
import com.example.projectservice.models.TestResults;
import com.example.userservice.models.User;
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
