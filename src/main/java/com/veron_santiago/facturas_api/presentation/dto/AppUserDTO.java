package com.veron_santiago.facturas_api.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AppUserDTO {

    @NotBlank
    @Size(min = 3, max = 50)
    final String username;
    @Email
    final String email;
    @NotBlank
    final String cuit;
    @NotBlank
    final String alias;
    @NotNull
    final List<Long> bills;

}
