package com.veron_santiago.facturas_api.presentation.controller;

import com.veron_santiago.facturas_api.presentation.dto.entities.BillDTO;
import com.veron_santiago.facturas_api.service.interfaces.IBillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {

    private final IBillService billService;

    public BillController(IBillService billService) {
        this.billService = billService;
    }

    @GetMapping("/{billId}")
    public ResponseEntity<BillDTO> getBillById(@PathVariable Long id) {
        return ResponseEntity.ok(billService.getBillById(id));
    }

    @GetMapping
    public ResponseEntity<List<BillDTO>> getAllBills() {
        return ResponseEntity.ok(billService.getAllBills());
    }

    @PostMapping
    public ResponseEntity<BillDTO> createBill(@RequestBody BillDTO billDto) {
        return ResponseEntity.ok(billService.createBill(billDto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBill(@PathVariable Long id){
        billService.deleteBill(id);
        return ResponseEntity.noContent().build();
    }
}
