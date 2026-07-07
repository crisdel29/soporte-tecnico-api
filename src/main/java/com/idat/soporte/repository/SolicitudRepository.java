package com.idat.soporte.repository;

import com.idat.soporte.model.Solicitud;
import org.springframework.stereotype.Repository;

@Repository
public class SolicitudRepository extends AbstractInMemoryRepository<Solicitud> {

    @Override
    protected Long obtenerId(Solicitud entity) {
        return entity.getId();
    }

    @Override
    protected void asignarId(Solicitud entity, Long id) {
        entity.setId(id);
    }
}
