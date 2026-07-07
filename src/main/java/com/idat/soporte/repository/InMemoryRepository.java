package com.idat.soporte.repository;

import java.util.List;
import java.util.Optional;

/**
 * Contrato generico para repositorios en memoria.
 * Al usar una interfaz, cualquier repositorio concreto (Cliente, Tecnico,
 * Solicitud) queda obligado a implementar las mismas operaciones basicas,
 * favoreciendo la reutilizacion y el bajo acoplamiento.
 *
 * @param <T>  tipo de la entidad
 * @param <ID> tipo del identificador
 */
public interface InMemoryRepository<T, ID> {

    T save(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    void deleteById(ID id);

    boolean existsById(ID id);
}
