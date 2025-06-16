package com.example.projectservice.dto;

import com.example.agreementservice.models.Agreement;
import com.example.projectservice.models.TestCommission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {

    private long projectId;

    private String name;

    private String description;

    private Set<Agreement> usersAgreements;

    private Set<TestCommission> usersCommission;
}
