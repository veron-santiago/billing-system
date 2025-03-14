package com.veron_santiago.facturas_api.presentation.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthCreateCompany(@NotBlank String companyName, @NotBlank String password, @NotBlank String email) {
}
