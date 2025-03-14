package com.veron_santiago.facturas_api.presentation.dto.entities;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class ProductDTO {

    @NotBlank
    final String name;
    @NotNull
    @DecimalMin("0.01")
    final BigDecimal price;
    @NotNull
    final List<Long> billItems;

}
