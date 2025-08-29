package com.api.crud.controllers;

import com.api.crud.dto.EstadoCuentaDTO;
import com.api.crud.services.EstadoCuentaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class EstadoCuentaController {

    private final EstadoCuentaService estadoCuentaService;

    public EstadoCuentaController(EstadoCuentaService estadoCuentaService) {
        this.estadoCuentaService = estadoCuentaService;
    }

    @GetMapping("/estado-cuenta")
    public ResponseEntity<List<EstadoCuentaDTO>> getEstadoCuenta(
            @RequestParam("clienteId") Long clienteId,
            @RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta
    ) {
        if (hasta.isBefore(desde)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El rango de fechas es inválido (hasta < desde).");
        }
        List<EstadoCuentaDTO> reporte = estadoCuentaService.generar(clienteId, desde, hasta);
        return ResponseEntity.ok(reporte);
    }
}