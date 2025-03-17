package com.veron_santiago.facturas_api.service.implementations.entity;

import com.veron_santiago.facturas_api.persistence.entity.BillItem;
import com.veron_santiago.facturas_api.persistence.entity.Product;
import com.veron_santiago.facturas_api.persistence.repository.IBillItemRepository;
import com.veron_santiago.facturas_api.persistence.repository.IBillRepository;
import com.veron_santiago.facturas_api.persistence.repository.IProductRepository;
import com.veron_santiago.facturas_api.presentation.dto.entities.BillItemDTO;
import com.veron_santiago.facturas_api.service.interfaces.IBillItemService;
import com.veron_santiago.facturas_api.util.mapper.BillItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillItemServiceImpl implements IBillItemService {

    private final IBillItemRepository billItemRepository;
    private final IProductRepository productRepository;
    private final BillItemMapper billItemMapper;
    private final IBillRepository billRepository;

    @Autowired
    public BillItemServiceImpl(IBillItemRepository billItemRepository, IProductRepository productRepository, BillItemMapper billItemMapper, IBillRepository billRepository) {
        this.billItemRepository = billItemRepository;
        this.productRepository = productRepository;
        this.billItemMapper = billItemMapper;
        this.billRepository = billRepository;
    }


    @Override
    public BillItemDTO createBillItem(BillItemDTO billItemDTO) {
        BillItem billItem = billItemMapper.billItemDTOToBillItem(billItemDTO);
        BillItem savedBillItem = billItemRepository.save(billItem);
        return billItemMapper.billItemToBillItemDTO(savedBillItem);
    }

    @Override
    public BillItemDTO getBillItemById(Long id) {
        BillItem billItem = billItemRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Item de factura no encontrado"));
        return billItemMapper.billItemToBillItemDTO(billItem);
    }

    @Override
    public List<BillItemDTO> getAllBillItems() {
        List<BillItem> billItems = billItemRepository.findAll();
        return billItems.stream()
                .map(billItemMapper::billItemToBillItemDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BillItemDTO updateBillItem(Long id, BillItemDTO billItemDTO) {
        BillItem existingBillItem = billItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el item de factura"));

        Product product = productRepository.findById(billItemDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("No se ha encontrado el producto"));

        existingBillItem.setProduct(product);
        existingBillItem.setQuantity(billItemDTO.getQuantity());
        existingBillItem.setUnitPrice(billItemDTO.getUnitPrice().doubleValue());
        existingBillItem.setBill(billRepository.findById(billItemDTO.getBillId())
                .orElseThrow(() -> new RuntimeException("No se ha encontrado la factura")));

        BigDecimal subtotal = billItemDTO.getUnitPrice().multiply(BigDecimal.valueOf(billItemDTO.getQuantity()));
        existingBillItem.setSubtotal(subtotal.doubleValue());

        billItemRepository.save(existingBillItem);

        return billItemMapper.billItemToBillItemDTO(existingBillItem);
    }

    @Override
    public void deleteBillItem(Long id) {
        BillItem billItem = billItemRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Item de factura no encontrado"));
        billItemRepository.deleteById(id);
    }

    @Override
    public List<BillItemDTO> getBillItemsByBillId(Long billId) {
        List<BillItem> billItems = billItemRepository.findByBillId(billId);
        return billItems.stream()
                .map(billItemMapper::billItemToBillItemDTO)
                .collect(Collectors.toList());
    }
}
