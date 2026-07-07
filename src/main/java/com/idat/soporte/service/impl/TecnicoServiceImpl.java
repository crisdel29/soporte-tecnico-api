package com.idat.soporte.service.impl;

import com.idat.soporte.dto.TecnicoDTO;
import com.idat.soporte.exception.ResourceNotFoundException;
import com.idat.soporte.model.Tecnico;
import com.idat.soporte.repository.TecnicoRepository;
import com.idat.soporte.service.TecnicoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TecnicoServiceImpl implements TecnicoService {

    private final TecnicoRepository tecnicoRepository;

    public TecnicoServiceImpl(TecnicoRepository tecnicoRepository) {
        this.tecnicoRepository = tecnicoRepository;
    }

    @Override
    public TecnicoDTO crear(TecnicoDTO dto) {
        Tecnico tecnico = new Tecnico(null, dto.getNombre(), dto.getEspecialidad(), dto.getEmail());
        Tecnico guardado = tecnicoRepository.save(tecnico);
        return toDTO(guardado);
    }

    @Override
    public TecnicoDTO obtenerPorId(Long id) {
        return toDTO(buscarOFallar(id));
    }

    @Override
    public List<TecnicoDTO> listarTodos() {
        List<Tecnico> tecnicos = tecnicoRepository.findAll();
        List<TecnicoDTO> resultado = new ArrayList<>();
        for (Tecnico tecnico : tecnicos) {
            resultado.add(toDTO(tecnico));
        }
        return resultado;
    }

    @Override
    public TecnicoDTO actualizar(Long id, TecnicoDTO dto) {
        Tecnico tecnico = buscarOFallar(id);
        tecnico.setNombre(dto.getNombre());
        tecnico.setEspecialidad(dto.getEspecialidad());
        tecnico.setEmail(dto.getEmail());
        return toDTO(tecnicoRepository.save(tecnico));
    }

    @Override
    public void eliminar(Long id) {
        buscarOFallar(id);
        tecnicoRepository.deleteById(id);
    }

    private Tecnico buscarOFallar(Long id) {
        return tecnicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tecnico no encontrado con id: " + id));
    }

    private TecnicoDTO toDTO(Tecnico t) {
        return new TecnicoDTO(t.getId(), t.getNombre(), t.getEspecialidad(), t.getEmail());
    }
}
