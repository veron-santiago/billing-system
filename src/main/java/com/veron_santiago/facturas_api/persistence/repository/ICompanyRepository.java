package com.veron_santiago.facturas_api.persistence.repository;

import com.veron_santiago.facturas_api.persistence.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByCompanyName(String companyName);
    Optional<Company> findByEmail(String email);
    Optional<Company> findByCompanyNameOrEmail(String companyNameOrEmail);
}
