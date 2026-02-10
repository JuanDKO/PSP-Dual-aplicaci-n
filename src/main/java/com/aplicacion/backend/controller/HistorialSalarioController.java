package com.aplicacion.backend.controller;

import com.aplicacion.backend.dtos.historial.HistorialSalarioCreateDTO;
import com.aplicacion.backend.dtos.historial.HistorialSalarioDTO;
import com.aplicacion.backend.service.HistorialSalarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historial-salarios")
@RequiredArgsConstructor

public class HistorialSalarioController {

    private final HistorialSalarioService historialSalarioService;

    @GetMapping
    public ResponseEntity<List<HistorialSalarioDTO>> getAll() {
        return ResponseEntity.ok(historialSalarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialSalarioDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(historialSalarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity<HistorialSalarioDTO> create(
            @RequestBody @jakarta.validation.Valid HistorialSalarioCreateDTO createDTO) {
        return new ResponseEntity<>(historialSalarioService.create(createDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HistorialSalarioDTO> update(@PathVariable Integer id,
            @RequestBody @jakarta.validation.Valid HistorialSalarioCreateDTO createDTO) {
        return ResponseEntity.ok(historialSalarioService.update(id, createDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        historialSalarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
