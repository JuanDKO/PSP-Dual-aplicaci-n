package com.aplicacion.backend.controller;

import com.aplicacion.backend.dtos.asistencia.AsistenciaCreateDTO;
import com.aplicacion.backend.dtos.asistencia.AsistenciaDTO;
import com.aplicacion.backend.service.AsistenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asistencias")
@RequiredArgsConstructor

public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    @GetMapping
    public ResponseEntity<List<AsistenciaDTO>> getAll() {
        return ResponseEntity.ok(asistenciaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsistenciaDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(asistenciaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<AsistenciaDTO> create(@RequestBody @jakarta.validation.Valid AsistenciaCreateDTO createDTO) {
        return new ResponseEntity<>(asistenciaService.create(createDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsistenciaDTO> update(@PathVariable Integer id,
            @RequestBody @jakarta.validation.Valid AsistenciaCreateDTO createDTO) {
        return ResponseEntity.ok(asistenciaService.update(id, createDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        asistenciaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
