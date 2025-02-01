package com.veron_santiago.facturas_api.persistance.repository;

import com.veron_santiago.facturas_api.persistance.entity.BillItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBillItemRepository extends JpaRepository<BillItem, Long> {
    List<BillItem> findByBillId(Long billId);
}
