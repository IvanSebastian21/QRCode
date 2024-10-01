package qrCode.service;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class QRCodeService {

    private static final Logger logger = LoggerFactory.getLogger(QRCodeService.class);

    public byte[] generateQRCode(String text) throws IOException {
        logger.info("Generando código QR para: {}", text);
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);

            BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, 400, 400);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < 200; i++) {
                for (int j = 0; j < 200; j++) {
                    if (bitMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "PNG", pngOutputStream);
            logger.info("Código QR generado exitosamente");
            return pngOutputStream.toByteArray();
        } catch (WriterException e) {
            throw new IOException("Error al generar el código QR", e);
        }
    }

    public String readQRCode(byte[] qrCodeImage) throws IOException {
        logger.info("Iniciando lectura de código QR");
        try {
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(qrCodeImage));
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                    new BufferedImageLuminanceSource(bufferedImage)));
            Result result = new MultiFormatReader().decode(binaryBitmap);
            logger.info("Código QR leído exitosamente");
            return result.getText();
        } catch (NotFoundException e) {
            logger.error("No se pudo encontrar un código QR en la imagen", e);
            throw new IOException("No se pudo encontrar un código QR en la imagen", e);
        } catch (Exception e) {
            logger.error("Error al leer el código QR", e);
            throw new IOException("Error al leer el código QR", e);
        }
    }

}
