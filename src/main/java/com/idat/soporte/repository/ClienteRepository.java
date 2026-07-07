package com.idat.soporte.repository;

import com.idat.soporte.model.Cliente;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteRepository extends AbstractInMemoryRepository<Cliente> {

    @Override
    protected Long obtenerId(Cliente entity) {
        return entity.getId();
    }

    @Override
    protected void asignarId(Cliente entity, Long id) {
        entity.setId(id);
    }
}
