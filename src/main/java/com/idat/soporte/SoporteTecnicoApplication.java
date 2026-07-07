package com.idat.soporte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal que arranca la aplicacion Spring Boot.
 * Punto de entrada del sistema de gestion de solicitudes de soporte tecnico.
 */
@SpringBootApplication
public class SoporteTecnicoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoporteTecnicoApplication.class, args);
    }
}
