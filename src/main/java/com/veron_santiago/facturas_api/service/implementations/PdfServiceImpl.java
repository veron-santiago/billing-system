package com.veron_santiago.facturas_api.service.implementations;

import com.google.zxing.WriterException;
import com.veron_santiago.facturas_api.service.interfaces.IPdfService;

import java.io.IOException;

public class PdfServiceImpl implements IPdfService {


    @Override
    public String generateTransferQR(String cuitOrAlias, Double monto) throws WriterException, IOException {
        return "";
    }

    @Override
    public String generateQR(String data) throws WriterException, IOException {
        return "";
    }
}
