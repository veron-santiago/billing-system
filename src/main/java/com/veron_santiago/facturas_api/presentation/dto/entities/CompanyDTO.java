package com.veron_santiago.facturas_api.presentation.dto.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CompanyDTO {

    @NotBlank
    @Size(min = 3, max = 50)
    final String companyName;
    @Email
    @NotBlank
    final String email;
    final String cuit;
    final String alias;
    @NotNull
    final List<Long> bills;

}
