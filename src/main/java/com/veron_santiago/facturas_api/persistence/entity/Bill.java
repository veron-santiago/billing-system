package com.veron_santiago.facturas_api.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String billNumber;
    @Column(nullable = false)
    private LocalDate issueDate;
    private LocalDate dueDate;
    @Column(nullable = false)
    private Double totalAmount;
    private String qrPath;
    private String barcodePath;
    @Column(nullable = false)
    private Boolean paymentStatus;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    Company company;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    Customer customer;

    /*@OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BillItem> items;*/

}
