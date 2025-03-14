package com.veron_santiago.facturas_api.persistence.repository;

import com.veron_santiago.facturas_api.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
