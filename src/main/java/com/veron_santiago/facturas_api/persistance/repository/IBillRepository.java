package com.veron_santiago.facturas_api.persistance.repository;

import com.veron_santiago.facturas_api.persistance.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBillRepository extends JpaRepository<Bill, Long> {
}
