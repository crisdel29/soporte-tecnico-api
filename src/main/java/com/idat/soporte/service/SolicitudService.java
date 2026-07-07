package com.idat.soporte.service;

import com.idat.soporte.dto.SolicitudRequestDTO;
import com.idat.soporte.dto.SolicitudResponseDTO;
import com.idat.soporte.model.EstadoSolicitud;

import java.util.List;

public interface SolicitudService {

    SolicitudResponseDTO crear(SolicitudRequestDTO dto);

    SolicitudResponseDTO obtenerPorId(Long id);

    List<SolicitudResponseDTO> listarTodas();

    List<SolicitudResponseDTO> listarPorEstado(EstadoSolicitud estado);

    SolicitudResponseDTO actualizar(Long id, SolicitudRequestDTO dto);

    SolicitudResponseDTO cambiarEstado(Long id, EstadoSolicitud nuevoEstado);

    void eliminar(Long id);
}
