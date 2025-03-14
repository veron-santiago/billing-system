package com.veron_santiago.facturas_api.persistence.repository;

import com.veron_santiago.facturas_api.persistence.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBillRepository extends JpaRepository<Bill, Long> {
}
