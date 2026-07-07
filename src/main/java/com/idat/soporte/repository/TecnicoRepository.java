package com.idat.soporte.repository;

import com.idat.soporte.model.Tecnico;
import org.springframework.stereotype.Repository;

@Repository
public class TecnicoRepository extends AbstractInMemoryRepository<Tecnico> {

    @Override
    protected Long obtenerId(Tecnico entity) {
        return entity.getId();
    }

    @Override
    protected void asignarId(Tecnico entity, Long id) {
        entity.setId(id);
    }
}
