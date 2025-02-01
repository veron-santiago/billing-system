package com.veron_santiago.facturas_api.persistance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {


    public AppUser(String username, String password, String email, String alias, String cuit, List<Bill> bills) {
        if ((alias == null || alias.isBlank()) && (cuit == null || cuit.isBlank())) {
            throw new IllegalArgumentException("Debe proporcionar al menos un alias o un cuit.");
        }
        this.username = username;
        this.password = password;
        this.email = email;
        this.alias = (alias == null) ? "" : alias;
        this.cuit = (cuit == null) ? "" : cuit;
        this.bills = bills;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Email
    private String email;
    private String cuit;
    private String alias;

    @OneToMany(mappedBy = "user")
    private List<Bill> bills;

}
