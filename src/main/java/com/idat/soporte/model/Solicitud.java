package com.idat.soporte.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad principal del sistema: representa una solicitud de soporte tecnico
 * registrada por un cliente y, opcionalmente, asignada a un tecnico.
 */
public class Solicitud {

    private Long id;
    private String titulo;
    private String descripcion;
    private Long clienteId;
    private Long tecnicoId; // puede ser null si aun no se asigna un tecnico
    private EstadoSolicitud estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    public Solicitud() {
    }

    public Solicitud(Long id, String titulo, String descripcion, Long clienteId,
                      Long tecnicoId, EstadoSolicitud estado,
                      LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.clienteId = clienteId;
        this.tecnicoId = tecnicoId;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Solicitud)) return false;
        Solicitud solicitud = (Solicitud) o;
        return Objects.equals(id, solicitud.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
