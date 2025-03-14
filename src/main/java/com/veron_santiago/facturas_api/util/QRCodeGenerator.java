package com.veron_santiago.facturas_api.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class QRCodeGenerator {

    private static final String BASE = "000201";
    private static final String PREFIX = "2636";
    private static final String AMOUNT_PREFIX = "54";
    private static final String COUNTRY_CODE = "5204AR";

    private static String generateQRData(String cuitOrAlias, Double monto) {

        if (cuitOrAlias == null || cuitOrAlias.isEmpty()) {
            throw new IllegalArgumentException("El CUIT o alias no puede estar vacío");
        }
        if (monto == null || monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser mayor que cero");
        }

        String amount = String.format("%.2f", monto).replace(".", "");

        StringBuilder qrData = new StringBuilder(BASE);
        qrData.append(PREFIX)
                .append(cuitOrAlias.length())
                .append(cuitOrAlias)
                .append(AMOUNT_PREFIX)
                .append(amount)
                .append(COUNTRY_CODE);
        return qrData.toString();
    }

    public static String generateTransferQR(String cuitOrAlias, Double monto) throws WriterException, IOException {
        String qrData = generateQRData(cuitOrAlias, monto);
        int width = 300;
        int height = 300;
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(qrData, BarcodeFormat.QR_CODE, width, height);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "PNG", baos);
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

}
