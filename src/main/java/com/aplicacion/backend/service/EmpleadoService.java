package com.aplicacion.backend.service;

import com.aplicacion.backend.domain.Empleado;
import com.aplicacion.backend.dtos.empleado.EmpleadoCreateDTO;
import com.aplicacion.backend.dtos.empleado.EmpleadoDTO;
import com.aplicacion.backend.mapper.EmpleadoMapper;
import com.aplicacion.backend.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoMapper empleadoMapper;

    public List<EmpleadoDTO> findAll() {
        return empleadoMapper.toDTO(empleadoRepository.findAll());
    }

    public EmpleadoDTO findById(Integer id) {
        return empleadoRepository.findById(id)
                .map(empleadoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Empleado not found"));
    }

    public EmpleadoDTO create(EmpleadoCreateDTO createDTO) {
        Empleado empleado = empleadoMapper.fromCreateUpdateDTO(createDTO);
        Empleado savedEmpleado = empleadoRepository.save(empleado);
        return empleadoMapper.toDTO(savedEmpleado);
    }

    public EmpleadoDTO update(Integer id, EmpleadoCreateDTO createDTO) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado not found"));

        empleadoMapper.updateEntityFromDTO(createDTO, empleado);
        Empleado updatedEmpleado = empleadoRepository.save(empleado);
        return empleadoMapper.toDTO(updatedEmpleado);
    }

    public void delete(Integer id) {
        if (!empleadoRepository.existsById(id)) {
            throw new RuntimeException("Empleado not found");
        }
        empleadoRepository.deleteById(id);
    }
}
