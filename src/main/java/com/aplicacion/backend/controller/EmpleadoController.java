package com.aplicacion.backend.controller;

import com.aplicacion.backend.dtos.empleado.EmpleadoCreateDTO;
import com.aplicacion.backend.dtos.empleado.EmpleadoDTO;
import com.aplicacion.backend.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor

public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> getAll() {
        return ResponseEntity.ok(empleadoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(empleadoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EmpleadoDTO> create(@RequestBody @jakarta.validation.Valid EmpleadoCreateDTO createDTO) {
        return new ResponseEntity<>(empleadoService.create(createDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> update(@PathVariable Integer id,
            @RequestBody @jakarta.validation.Valid EmpleadoCreateDTO createDTO) {
        return ResponseEntity.ok(empleadoService.update(id, createDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        empleadoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
