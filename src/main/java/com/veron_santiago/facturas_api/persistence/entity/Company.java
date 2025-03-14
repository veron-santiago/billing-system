package com.veron_santiago.facturas_api.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String companyName;
    @Column(nullable = false)
    @JsonIgnore
    private String password;
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    @Column(unique = true)
    private String cuit;
    @Column(unique = true)
    private String alias;
    private boolean verified = false;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Bill> bills;

}
