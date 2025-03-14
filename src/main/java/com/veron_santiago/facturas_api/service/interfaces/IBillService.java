package com.veron_santiago.facturas_api.service.interfaces;

import com.veron_santiago.facturas_api.presentation.dto.entities.BillDTO;

import java.util.List;

public interface IBillService {

    BillDTO createBill(BillDTO billDTO);
    BillDTO getBillById(Long id);
    List<BillDTO> getAllBills();
    void deleteBill(Long id);
    byte[] generateBillPDF(Long id);

}
