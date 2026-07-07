package com.idat.soporte.service;

import com.idat.soporte.dto.TecnicoDTO;

import java.util.List;

public interface TecnicoService {

    TecnicoDTO crear(TecnicoDTO dto);

    TecnicoDTO obtenerPorId(Long id);

    List<TecnicoDTO> listarTodos();

    TecnicoDTO actualizar(Long id, TecnicoDTO dto);

    void eliminar(Long id);
}
