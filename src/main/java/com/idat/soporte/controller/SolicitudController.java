package com.idat.soporte.controller;

import com.idat.soporte.dto.SolicitudRequestDTO;
import com.idat.soporte.dto.SolicitudResponseDTO;
import com.idat.soporte.model.EstadoSolicitud;
import com.idat.soporte.service.SolicitudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST principal: expone el CRUD completo de Solicitud de soporte
 * tecnico, ademas de un endpoint extra para cambiar el estado de una solicitud
 * (util para el flujo real de atencion: PENDIENTE -> EN_PROCESO -> RESUELTA).
 */
@RestController
@RequestMapping("/api/solicitudes")
@Tag(name = "Solicitudes", description = "CRUD de solicitudes de soporte tecnico")
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @Operation(summary = "Registrar una nueva solicitud de soporte")
    @PostMapping
    public ResponseEntity<SolicitudResponseDTO> crear(@Valid @RequestBody SolicitudRequestDTO dto) {
        SolicitudResponseDTO creada = solicitudService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

    @Operation(summary = "Listar todas las solicitudes, opcionalmente filtradas por estado")
    @GetMapping
    public ResponseEntity<List<SolicitudResponseDTO>> listar(
            @RequestParam(required = false) EstadoSolicitud estado) {
        if (estado != null) {
            return ResponseEntity.ok(solicitudService.listarPorEstado(estado));
        }
        return ResponseEntity.ok(solicitudService.listarTodas());
    }

    @Operation(summary = "Consultar el detalle de una solicitud por id")
    @GetMapping("/{id}")
    public ResponseEntity<SolicitudResponseDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(solicitudService.obtenerPorId(id));
    }

    @Operation(summary = "Actualizar los datos de una solicitud existente")
    @PutMapping("/{id}")
    public ResponseEntity<SolicitudResponseDTO> actualizar(@PathVariable Long id,
                                                             @Valid @RequestBody SolicitudRequestDTO dto) {
        return ResponseEntity.ok(solicitudService.actualizar(id, dto));
    }

    @Operation(summary = "Cambiar el estado de una solicitud (ej. a EN_PROCESO o RESUELTA)")
    @PatchMapping("/{id}/estado")
    public ResponseEntity<SolicitudResponseDTO> cambiarEstado(@PathVariable Long id,
                                                                @RequestParam EstadoSolicitud estado) {
        return ResponseEntity.ok(solicitudService.cambiarEstado(id, estado));
    }

    @Operation(summary = "Eliminar una solicitud")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        solicitudService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
