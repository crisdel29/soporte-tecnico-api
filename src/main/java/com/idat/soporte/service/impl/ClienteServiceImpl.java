package com.idat.soporte.service.impl;

import com.idat.soporte.dto.ClienteDTO;
import com.idat.soporte.exception.ResourceNotFoundException;
import com.idat.soporte.model.Cliente;
import com.idat.soporte.repository.ClienteRepository;
import com.idat.soporte.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteDTO crear(ClienteDTO dto) {
        Cliente cliente = new Cliente(null, dto.getNombre(), dto.getEmail(), dto.getTelefono());
        Cliente guardado = clienteRepository.save(cliente);
        return toDTO(guardado);
    }

    @Override
    public ClienteDTO obtenerPorId(Long id) {
        Cliente cliente = buscarOFallar(id);
        return toDTO(cliente);
    }

    @Override
    public List<ClienteDTO> listarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTO> resultado = new ArrayList<>();
        for (Cliente cliente : clientes) {
            resultado.add(toDTO(cliente));
        }
        return resultado;
    }

    @Override
    public ClienteDTO actualizar(Long id, ClienteDTO dto) {
        Cliente cliente = buscarOFallar(id);
        cliente.setNombre(dto.getNombre());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());
        Cliente actualizado = clienteRepository.save(cliente);
        return toDTO(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        buscarOFallar(id);
        clienteRepository.deleteById(id);
    }

    private Cliente buscarOFallar(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
    }

    private ClienteDTO toDTO(Cliente c) {
        return new ClienteDTO(c.getId(), c.getNombre(), c.getEmail(), c.getTelefono());
    }
}
