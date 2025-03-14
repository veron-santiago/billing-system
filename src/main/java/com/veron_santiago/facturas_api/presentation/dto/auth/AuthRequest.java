package com.veron_santiago.facturas_api.presentation.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(@NotBlank String companyName,
                          @NotBlank String password) {
}
