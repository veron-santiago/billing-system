package com.veron_santiago.facturas_api.service.interfaces;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface IPdfService {
    String generateTransferQR(String cuitOrAlias, Double monto) throws WriterException, IOException;
    String generateQR(String data) throws WriterException, IOException;
}
