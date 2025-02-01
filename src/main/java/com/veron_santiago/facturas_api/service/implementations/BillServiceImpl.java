package com.veron_santiago.facturas_api.service.implementations;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.veron_santiago.facturas_api.persistance.entity.Bill;
import com.veron_santiago.facturas_api.persistance.repository.IBillRepository;
import com.veron_santiago.facturas_api.service.interfaces.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class BillServiceImpl implements IBillService {

    private final IBillRepository billRepository;

    @Autowired
    public BillServiceImpl(IBillRepository billRepository) {
        this.billRepository = billRepository;
    }


    @Override
    public Bill createBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Bill getBillById(Long id) {
        return billRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Factura no encontrada"));
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
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
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Factura #" + id));
            document.add(new Paragraph("Cliente: Juan Pérez"));
            document.add(new Paragraph("Total: $100.00"));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }

}
