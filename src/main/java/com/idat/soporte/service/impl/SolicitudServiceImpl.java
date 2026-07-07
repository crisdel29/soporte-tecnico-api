package com.idat.soporte.service.impl;

import com.idat.soporte.dto.ClienteDTO;
import com.idat.soporte.dto.SolicitudRequestDTO;
import com.idat.soporte.dto.SolicitudResponseDTO;
import com.idat.soporte.dto.TecnicoDTO;
import com.idat.soporte.exception.ResourceNotFoundException;
import com.idat.soporte.model.Cliente;
import com.idat.soporte.model.EstadoSolicitud;
import com.idat.soporte.model.Solicitud;
import com.idat.soporte.model.Tecnico;
import com.idat.soporte.repository.ClienteRepository;
import com.idat.soporte.repository.SolicitudRepository;
import com.idat.soporte.repository.TecnicoRepository;
import com.idat.soporte.service.SolicitudService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final ClienteRepository clienteRepository;
    private final TecnicoRepository tecnicoRepository;

    public SolicitudServiceImpl(SolicitudRepository solicitudRepository,
                                 ClienteRepository clienteRepository,
                                 TecnicoRepository tecnicoRepository) {
        this.solicitudRepository = solicitudRepository;
        this.clienteRepository = clienteRepository;
        this.tecnicoRepository = tecnicoRepository;
    }

    @Override
    public SolicitudResponseDTO crear(SolicitudRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se puede crear la solicitud: cliente no encontrado con id " + dto.getClienteId()));

        Tecnico tecnico = null;
        if (dto.getTecnicoId() != null) {
            tecnico = tecnicoRepository.findById(dto.getTecnicoId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "No se puede crear la solicitud: tecnico no encontrado con id " + dto.getTecnicoId()));
        }

        LocalDateTime ahora = LocalDateTime.now();
        Solicitud solicitud = new Solicitud(
                null,
                dto.getTitulo(),
                dto.getDescripcion(),
                cliente.getId(),
                tecnico != null ? tecnico.getId() : null,
                EstadoSolicitud.PENDIENTE,
                ahora,
                ahora
        );

        Solicitud guardada = solicitudRepository.save(solicitud);
        return toResponseDTO(guardada);
    }

    @Override
    public SolicitudResponseDTO obtenerPorId(Long id) {
        return toResponseDTO(buscarOFallar(id));
    }

    @Override
    public List<SolicitudResponseDTO> listarTodas() {
        List<Solicitud> solicitudes = solicitudRepository.findAll();
        List<SolicitudResponseDTO> resultado = new ArrayList<>();
        for (Solicitud solicitud : solicitudes) {
            resultado.add(toResponseDTO(solicitud));
        }
        return resultado;
    }

    @Override
    public List<SolicitudResponseDTO> listarPorEstado(EstadoSolicitud estado) {
        List<Solicitud> solicitudes = solicitudRepository.findAll();
        List<SolicitudResponseDTO> resultado = new ArrayList<>();
        for (Solicitud solicitud : solicitudes) {
            if (solicitud.getEstado() == estado) {
                resultado.add(toResponseDTO(solicitud));
            }
        }
        return resultado;
    }

    @Override
    public SolicitudResponseDTO actualizar(Long id, SolicitudRequestDTO dto) {
        Solicitud solicitud = buscarOFallar(id);

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cliente no encontrado con id " + dto.getClienteId()));

        if (dto.getTecnicoId() != null) {
            Tecnico tecnico = tecnicoRepository.findById(dto.getTecnicoId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Tecnico no encontrado con id " + dto.getTecnicoId()));
            solicitud.setTecnicoId(tecnico.getId());
        } else {
            solicitud.setTecnicoId(null);
        }

        solicitud.setTitulo(dto.getTitulo());
        solicitud.setDescripcion(dto.getDescripcion());
        solicitud.setClienteId(cliente.getId());
        solicitud.setFechaActualizacion(LocalDateTime.now());

        Solicitud actualizada = solicitudRepository.save(solicitud);
        return toResponseDTO(actualizada);
    }

    @Override
    public SolicitudResponseDTO cambiarEstado(Long id, EstadoSolicitud nuevoEstado) {
        Solicitud solicitud = buscarOFallar(id);
        solicitud.setEstado(nuevoEstado);
        solicitud.setFechaActualizacion(LocalDateTime.now());
        return toResponseDTO(solicitudRepository.save(solicitud));
    }

    @Override
    public void eliminar(Long id) {
        buscarOFallar(id);
        solicitudRepository.deleteById(id);
    }

    private Solicitud buscarOFallar(Long id) {
        return solicitudRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada con id: " + id));
    }

    private SolicitudResponseDTO toResponseDTO(Solicitud s) {
        Cliente cliente = clienteRepository.findById(s.getClienteId()).orElse(null);
        Tecnico tecnico = s.getTecnicoId() != null
                ? tecnicoRepository.findById(s.getTecnicoId()).orElse(null)
                : null;

        ClienteDTO clienteDTO = cliente != null
                ? new ClienteDTO(cliente.getId(), cliente.getNombre(), cliente.getEmail(), cliente.getTelefono())
                : null;

        TecnicoDTO tecnicoDTO = tecnico != null
                ? new TecnicoDTO(tecnico.getId(), tecnico.getNombre(), tecnico.getEspecialidad(), tecnico.getEmail())
                : null;

        return new SolicitudResponseDTO(
                s.getId(),
                s.getTitulo(),
                s.getDescripcion(),
                clienteDTO,
                tecnicoDTO,
                s.getEstado(),
                s.getFechaCreacion(),
                s.getFechaActualizacion()
        );
    }
}
