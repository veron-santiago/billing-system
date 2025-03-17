package com.veron_santiago.facturas_api.service.implementations.entity;

import com.veron_santiago.facturas_api.persistence.entity.Bill;
import com.veron_santiago.facturas_api.persistence.repository.IBillRepository;
import com.veron_santiago.facturas_api.presentation.dto.entities.BillDTO;
import com.veron_santiago.facturas_api.service.interfaces.IBillService;
import com.veron_santiago.facturas_api.util.mapper.BillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements IBillService {

    private final IBillRepository billRepository;
    private final BillMapper billMapper;

    @Autowired
    public BillServiceImpl(IBillRepository billRepository, BillMapper billMapper) {
        this.billRepository = billRepository;
        this.billMapper = billMapper;
    }


    @Override
    public BillDTO createBill(BillDTO billDTO) {
        Bill bill = billMapper.billDTOToBill(billDTO);
        Bill savedBill = billRepository.save(bill);
        return billMapper.billToBillDTO(savedBill);
    }

    @Override
    public BillDTO getBillById(Long id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Factura no encontrada"));
        return billMapper.billToBillDTO(bill);
    }

    @Override
    public List<BillDTO> getAllBills() {
        List<Bill> bills = billRepository.findAll();
        return bills.stream()
                .map(billMapper::billToBillDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBill(Long id) {
        billRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Factura no encontrada"));
        billRepository.deleteById(id);
    }

    @Override
    public byte[] generateBillPDF(Long id) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
        } catch (Exception e) {
        }

        return baos.toByteArray();
    }

}
