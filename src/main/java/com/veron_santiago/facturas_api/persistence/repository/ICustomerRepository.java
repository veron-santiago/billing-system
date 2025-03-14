package com.veron_santiago.facturas_api.persistence.repository;

import com.veron_santiago.facturas_api.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}
