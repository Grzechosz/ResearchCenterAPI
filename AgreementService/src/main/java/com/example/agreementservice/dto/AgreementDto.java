package com.example.agreementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgreementDto {

    private Long agreementId;

    private Long userId;

    private Long projectId;

    private ProjectDto project;
}
