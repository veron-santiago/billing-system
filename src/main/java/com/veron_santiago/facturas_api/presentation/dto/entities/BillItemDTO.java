package com.veron_santiago.facturas_api.presentation.dto.entities;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class BillItemDTO {

    @Min(1)
    @NotNull
    final Integer quantity;
    @DecimalMin("0.00")
    @NotNull
    final BigDecimal unitPrice;
    final Long billId;
    final Long productId;

    public BigDecimal getSubtotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

}
