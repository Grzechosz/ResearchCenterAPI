package com.denert.app.rest.dto;

import com.denert.app.rest.models.Agreement;
import com.denert.app.rest.models.TestCommission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest {

    private String name;

    private String description;

    private Set<Agreement> usersAgreements;

    private Set<TestCommission> usersCommission;
}
