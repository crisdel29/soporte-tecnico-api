package com.idat.soporte.model;

/**
 * Representa los posibles estados de una solicitud de soporte tecnico.
 * Usar un enum en lugar de un String evita valores invalidos y mejora
 * la mantenibilidad y la claridad del codigo (sintaxis tipada).
 */
public enum EstadoSolicitud {
    PENDIENTE,
    EN_PROCESO,
    RESUELTA,
    CANCELADA
}
