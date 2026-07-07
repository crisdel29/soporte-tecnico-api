package com.idat.soporte.model;

import java.util.Objects;

/**
 * Entidad que representa a un tecnico encargado de atender solicitudes.
 */
public class Tecnico {

    private Long id;
    private String nombre;
    private String especialidad;
    private String email;

    public Tecnico() {
    }

    public Tecnico(Long id, String nombre, String especialidad, String email) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tecnico)) return false;
        Tecnico tecnico = (Tecnico) o;
        return Objects.equals(id, tecnico.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
