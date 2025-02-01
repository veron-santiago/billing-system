package com.veron_santiago.facturas_api.service.implementations;

import com.veron_santiago.facturas_api.persistance.entity.BillItem;
import com.veron_santiago.facturas_api.persistance.repository.IBillItemRepository;
import com.veron_santiago.facturas_api.service.interfaces.IBillItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillItemServiceImpl implements IBillItemService {

    private final IBillItemRepository billItemRepository;

    @Autowired
    public BillItemServiceImpl(IBillItemRepository billItemRepository) {
        this.billItemRepository = billItemRepository;
    }


    @Override
    public BillItem createBillItem(BillItem billItem) {
        return billItemRepository.save(billItem);
    }

    @Override
    public BillItem getBillItemById(Long id) {
        return billItemRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Item de factura no encontrado"));
    }

    @Override
    public List<BillItem> getAllBillItems() {
        return billItemRepository.findAll();
    }

    @Override
    public BillItem updateBillItem(Long id, BillItem billItem) {
        BillItem existingBillItem = billItemRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("No se ha encontrado el item de factura"));
        existingBillItem.setProduct(billItem.getProduct());
        existingBillItem.setQuantity(billItem.getQuantity());
        existingBillItem.setUnitPrice(billItem.getUnitPrice());
        existingBillItem.setBill(billItem.getBill());
        existingBillItem.setSubtotal(billItem.getUnitPrice() * billItem.getQuantity());

        return billItemRepository.save(billItem);
    }

    @Override
    public void deleteBillItem(Long id) {
        BillItem billItem = billItemRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Item de factura no encontrado"));
        billItemRepository.deleteById(id);
    }

    @Override
    public List<BillItem> getBillItemsByBillId(Long billId) {
        return billItemRepository.findByBillId(billId);
    }
}
