package com.idat.soporte.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO utilizado para crear o actualizar una Solicitud.
 * Las anotaciones de validacion garantizan que los datos de entrada
 * sean correctos antes de llegar a la logica de negocio.
 */
public class SolicitudRequestDTO {

    @NotBlank(message = "El titulo es obligatorio")
    @Size(max = 100, message = "El titulo no debe superar los 100 caracteres")
    private String titulo;

    @NotBlank(message = "La descripcion es obligatoria")
    @Size(max = 500, message = "La descripcion no debe superar los 500 caracteres")
    private String descripcion;

    @NotNull(message = "El id del cliente es obligatorio")
    private Long clienteId;

    // Opcional: una solicitud puede crearse sin tecnico asignado todavia
    private Long tecnicoId;

    public SolicitudRequestDTO() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getTecnicoId() {
        return tecnicoId;
    }

    public void setTecnicoId(Long tecnicoId) {
        this.tecnicoId = tecnicoId;
    }
}
