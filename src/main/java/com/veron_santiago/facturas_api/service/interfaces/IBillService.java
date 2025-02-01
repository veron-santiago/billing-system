package com.veron_santiago.facturas_api.service.interfaces;

import com.veron_santiago.facturas_api.persistance.entity.Bill;

import java.util.List;

public interface IBillService {

    Bill createBill(Bill bill);
    Bill getBillById(Long id);
    List<Bill> getAllBills();
    void deleteBill(Long id);
    byte[] generateBillPDF(Long id);

}
