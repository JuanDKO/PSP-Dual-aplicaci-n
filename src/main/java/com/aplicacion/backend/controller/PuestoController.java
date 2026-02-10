package com.aplicacion.backend.controller;

import com.aplicacion.backend.dtos.puesto.PuestoCreateDTO;
import com.aplicacion.backend.dtos.puesto.PuestoDTO;
import com.aplicacion.backend.service.PuestoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/puestos")
@RequiredArgsConstructor

public class PuestoController {

    private final PuestoService puestoService;

    @GetMapping
    public ResponseEntity<List<PuestoDTO>> getAll() {
        return ResponseEntity.ok(puestoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PuestoDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(puestoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PuestoDTO> create(@RequestBody @jakarta.validation.Valid PuestoCreateDTO createDTO) {
        return new ResponseEntity<>(puestoService.create(createDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PuestoDTO> update(@PathVariable Integer id,
            @RequestBody @jakarta.validation.Valid PuestoCreateDTO createDTO) {
        return ResponseEntity.ok(puestoService.update(id, createDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        puestoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
