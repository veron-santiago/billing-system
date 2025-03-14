package com.veron_santiago.facturas_api.service.interfaces;

import com.veron_santiago.facturas_api.presentation.dto.entities.BillItemDTO;

import java.util.List;

public interface IBillItemService {
    BillItemDTO createBillItem(BillItemDTO billItemDTO);
    BillItemDTO getBillItemById(Long id);
    List<BillItemDTO> getAllBillItems();
    BillItemDTO updateBillItem(Long id, BillItemDTO billItemDTO);
    void deleteBillItem(Long id);
    List<BillItemDTO> getBillItemsByBillId(Long billId);
}
