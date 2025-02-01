package com.veron_santiago.facturas_api.service.interfaces;

import com.veron_santiago.facturas_api.persistance.entity.BillItem;

import java.util.List;

public interface IBillItemService {
    BillItem createBillItem(BillItem billItem);
    BillItem getBillItemById(Long id);
    List<BillItem> getAllBillItems();
    BillItem updateBillItem(Long id, BillItem billItem);
    void deleteBillItem(Long id);
    List<BillItem> getBillItemsByBillId(Long billId);
}
