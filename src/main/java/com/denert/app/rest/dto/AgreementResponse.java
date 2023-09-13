package com.denert.app.rest.dto;

import com.denert.app.rest.models.Project;
import com.denert.app.rest.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgreementResponse {

    private Long agreementId;

    private User user;

    private Project project;
}
