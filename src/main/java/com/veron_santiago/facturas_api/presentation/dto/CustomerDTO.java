package com.veron_santiago.facturas_api.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CustomerDTO {

    @NotBlank
    final String name;
    final String email;
    @NotNull
    final List<Long> bills;

}
