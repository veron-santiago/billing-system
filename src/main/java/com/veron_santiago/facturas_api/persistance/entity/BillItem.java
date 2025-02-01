package com.veron_santiago.facturas_api.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Double unitPrice;
    private Double subtotal;

    @ManyToOne
    private Bill bill;

    @ManyToOne
    private Product product;

}
