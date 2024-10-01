package qrCode.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import qrCode.service.QRCodeService;

import java.io.IOException;

@RestController
@RequestMapping("/api/qrcode")
public class QRController {

    private static final Logger logger = LoggerFactory.getLogger(QRController.class);

    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping(value = "/generate", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<?> generateQRCode() {
        try {
            logger.info("Iniciando generación de código QR");
            byte[] qrCode = qrCodeService.generateQRCode("Hola Oscar querido. Feliz cumple desde un QR!");
            logger.info("Código QR generado exitosamente");
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(qrCode);
        } catch (Exception e) {
            logger.error("Error al generar el código QR", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al generar el código QR: " + e.getMessage());
        }
    }

    @PostMapping("/read")
    public ResponseEntity<?> readQRCode(@RequestParam("file") MultipartFile file) {
        try {
            logger.info("Recibiendo archivo para leer código QR");
            byte[] imageBytes = file.getBytes();
            String qrCodeText = qrCodeService.readQRCode(imageBytes);
            logger.info("Código QR leído exitosamente");
            return ResponseEntity.ok(qrCodeText);
        } catch (IOException e) {
            logger.error("Error al leer el archivo o el código QR", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al leer el código QR: " + e.getMessage());
        }
    }

}
