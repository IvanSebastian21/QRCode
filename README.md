Microservice generated QRCode

# QR Code Generator and Reader

Este proyecto es una aplicación Spring Boot que proporciona servicios para generar y leer códigos QR. Permite la creación de códigos QR a partir de texto o URLs, así como la lectura y decodificación de imágenes de códigos QR existentes.

## Características

- Generación de códigos QR a partir de texto o URLs
- Lectura y decodificación de imágenes de códigos QR
- Redirección automática para códigos QR que contienen URLs válidas
- Manejo de errores y logging detallado

## Requisitos previos

- Java 17 o superior
- Maven 3.6.0 o superior
- Spring Boot 2.6.3 o superior

## Instalación

1. Clona el repositorio:
   ```
   git clone https://github.com/IvanSebastian21/QRCode.git
   ```

2. Navega al directorio del proyecto:
   ```
   cd qr-code-project
   ```

3. Compila el proyecto con Maven:
   ```
   mvn clean install
   ```

## Uso

### Iniciar la aplicación

Ejecuta la aplicación con el siguiente comando:

```
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`.

### Generar un código QR

Para generar un código QR, realiza una petición GET a:

```
http://localhost:8080/api/qrcode/generate?url=https://www.ejemplo.com
```

Si no se proporciona una URL, se generará un código QR con el texto "Hola desde QR".

### Leer un código QR

Para leer un código QR, envía una petición POST a:

```
http://localhost:8080/api/qrcode/read
```

Incluye la imagen del código QR como un archivo multipart con el nombre "file".

## Estructura del proyecto

```
src
└── main
    └── java
        └── com
            └── myApp
                └── qrcode
                    ├── QrCodeApplication.java
                    ├── controller
                    │   └── QRCodeController.java
                    └── service
                        └── QRCodeService.java
```

## Tecnologías utilizadas

- Spring Boot
- ZXing ("Zebra Crossing") para la generación y lectura de códigos QR
- SLF4J para logging

## Contribuir

Las contribuciones son bienvenidas. Por favor, abre un issue para discutir los cambios propuestos antes de realizar un pull request.

## Contacto

- Correo electrónico: ivanseba.nez20@gmail.com
- LinkedIn: [Iván S. Nuñez](https://www.linkedin.com/in/ivan-s-nu%C3%B1ez/)

Enlace del proyecto: [https://github.com/IvanSebastian21/QRCode/main/README.md](https://github.com/IvanSebastian21/QRCode/main/README.md)

