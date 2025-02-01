package com.veron_santiago.facturas_api.presentation.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class BillDTO {

    @NotBlank
    final String billNumber;
    @NotEmpty
    @PastOrPresent
    final LocalDate issueDate;
    final LocalDate dueDate;
    @NotNull
    @DecimalMin("0.00")
    final BigDecimal amount;
    final String qrPath;
    final String barcodePath;
    @NotNull
    final Boolean paymentStatus;
    @NotNull
    final Long userId;
    @NotNull
    final Long customerId;
    @NotNull
    final List<Long> items;

}
