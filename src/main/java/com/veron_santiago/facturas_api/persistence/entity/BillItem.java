package com.veron_santiago.facturas_api.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor 
public class BillItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Double unitPrice;
    @Column(nullable = false)
    private Double subtotal;

/*    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = false)
    private Bill bill;*/

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
