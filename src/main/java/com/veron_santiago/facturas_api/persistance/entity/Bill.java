package com.veron_santiago.facturas_api.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String billNumber;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private Double amount;
    private String qrPath;
    private String barcodePath;
    private Boolean paymentStatus;

    @ManyToOne
    AppUser user;

    @ManyToOne
    Customer customer;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
    private List<BillItem> items;

}
