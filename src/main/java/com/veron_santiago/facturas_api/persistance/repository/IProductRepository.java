package com.veron_santiago.facturas_api.persistance.repository;

import com.veron_santiago.facturas_api.persistance.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
