package com.idat.soporte.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Clase abstracta que implementa la logica comun de un repositorio en
 * memoria, respaldada por un HashMap (clave = id, valor = entidad).
 *
 * Las clases hijas (ClienteRepository, TecnicoRepository, SolicitudRepository)
 * heredan todo el CRUD y solo deben indicar, mediante los metodos abstractos
 * obtenerId() y asignarId(), como leer y escribir el id de su propia entidad.
 * Esto es un ejemplo de HERENCIA y POLIMORFISMO: cada subclase completa el
 * comportamiento especifico sin repetir la logica de guardar/buscar/eliminar.
 *
 * @param <T> tipo de la entidad (Cliente, Tecnico o Solicitud)
 */
public abstract class AbstractInMemoryRepository<T> implements InMemoryRepository<T, Long> {

    protected Map<Long, T> data = new HashMap<>();
    protected long contadorId = 0;

    // Cada subclase implementa como obtener el id de su propia entidad
    protected abstract Long obtenerId(T entity);

    // Cada subclase implementa como asignar el id a su propia entidad
    protected abstract void asignarId(T entity, Long id);

    @Override
    public T save(T entity) {
        Long id = obtenerId(entity);
        if (id == null) {
            contadorId = contadorId + 1;
            id = contadorId;
            asignarId(entity, id);
        }
        data.put(id, entity);
        return entity;
    }

    @Override
    public Optional<T> findById(Long id) {
        T entity = data.get(id);
        return Optional.ofNullable(entity);
    }

    @Override
    public List<T> findAll() {
        List<T> lista = new ArrayList<>();
        for (T entity : data.values()) {
            lista.add(entity);
        }
        return lista;
    }

    @Override
    public void deleteById(Long id) {
        data.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return data.containsKey(id);
    }
}
