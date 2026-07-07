package com.idat.soporte.service;

import com.idat.soporte.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {

    ClienteDTO crear(ClienteDTO dto);

    ClienteDTO obtenerPorId(Long id);

    List<ClienteDTO> listarTodos();

    ClienteDTO actualizar(Long id, ClienteDTO dto);

    void eliminar(Long id);
}
