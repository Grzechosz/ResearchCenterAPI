package com.example.agreementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgreementResponse {
    private AgreementDto agreement;
    private ProjectDto project;
    private UserDto user;
}
