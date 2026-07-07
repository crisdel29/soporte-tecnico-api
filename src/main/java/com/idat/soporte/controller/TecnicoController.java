package com.idat.soporte.controller;

import com.idat.soporte.dto.TecnicoDTO;
import com.idat.soporte.service.TecnicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tecnicos")
@Tag(name = "Tecnicos", description = "Gestion de tecnicos que atienden solicitudes de soporte")
public class TecnicoController {

    private final TecnicoService tecnicoService;

    public TecnicoController(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    @Operation(summary = "Registrar un nuevo tecnico")
    @PostMapping
    public ResponseEntity<TecnicoDTO> crear(@Valid @RequestBody TecnicoDTO dto) {
        TecnicoDTO creado = tecnicoService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @Operation(summary = "Listar todos los tecnicos")
    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> listar() {
        return ResponseEntity.ok(tecnicoService.listarTodos());
    }

    @Operation(summary = "Obtener un tecnico por id")
    @GetMapping("/{id}")
    public ResponseEntity<TecnicoDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(tecnicoService.obtenerPorId(id));
    }

    @Operation(summary = "Actualizar los datos de un tecnico")
    @PutMapping("/{id}")
    public ResponseEntity<TecnicoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody TecnicoDTO dto) {
        return ResponseEntity.ok(tecnicoService.actualizar(id, dto));
    }

    @Operation(summary = "Eliminar un tecnico")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tecnicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
