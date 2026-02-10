package com.aplicacion.backend.controller;

import com.aplicacion.backend.dtos.departamento.DepartamentoCreateDTO;
import com.aplicacion.backend.dtos.departamento.DepartamentoDTO;
import com.aplicacion.backend.service.DepartamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
@RequiredArgsConstructor

public class DepartamentoController {

    private final DepartamentoService departamentoService;

    @GetMapping
    public ResponseEntity<List<DepartamentoDTO>> getAll() {
        return ResponseEntity.ok(departamentoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(departamentoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<DepartamentoDTO> create(
            @RequestBody @jakarta.validation.Valid DepartamentoCreateDTO createDTO) {
        return new ResponseEntity<>(departamentoService.create(createDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartamentoDTO> update(@PathVariable Integer id,
            @RequestBody @jakarta.validation.Valid DepartamentoCreateDTO createDTO) {
        return ResponseEntity.ok(departamentoService.update(id, createDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        departamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
