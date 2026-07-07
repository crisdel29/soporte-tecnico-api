package com.idat.soporte.exception;

/**
 * Excepcion lanzada cuando se busca un recurso (Solicitud, Cliente, Tecnico)
 * que no existe en el sistema. Sera capturada por el GlobalExceptionHandler
 * y traducida a una respuesta HTTP 404.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
