package com.aplicacion.backend.service;

import com.aplicacion.backend.domain.Departamento;
import com.aplicacion.backend.domain.Empleado;
import com.aplicacion.backend.domain.Puesto;
import com.aplicacion.backend.dtos.empleado.EmpleadoCreateDTO;
import com.aplicacion.backend.dtos.empleado.EmpleadoDTO;
import com.aplicacion.backend.mapper.EmpleadoMapper;
import com.aplicacion.backend.repository.DepartamentoRepository;
import com.aplicacion.backend.repository.EmpleadoRepository;
import com.aplicacion.backend.repository.PuestoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final PuestoRepository puestoRepository;
    private final DepartamentoRepository departamentoRepository;
    private final EmpleadoMapper empleadoMapper;

    public List<EmpleadoDTO> findAll() {
        return empleadoMapper.toDTO(empleadoRepository.findAll());
    }

    public EmpleadoDTO findById(Integer id) {
        return empleadoRepository.findById(id)
                .map(empleadoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + id));
    }

    public EmpleadoDTO create(EmpleadoCreateDTO createDTO) {
        // Verificar que Puesto existe
        Puesto puesto = null;
        if (createDTO.getIdPuesto() != null) {
            puesto = puestoRepository.findById(createDTO.getIdPuesto())
                    .orElseThrow(
                            () -> new RuntimeException("El Puesto con ID " + createDTO.getIdPuesto() + " no existe"));
        }

        // Verificar que Departamento existe
        Departamento departamento = null;
        if (createDTO.getIdDepartamento() != null) {
            departamento = departamentoRepository.findById(createDTO.getIdDepartamento())
                    .orElseThrow(() -> new RuntimeException(
                            "El Departamento con ID " + createDTO.getIdDepartamento() + " no existe"));
        }

        // Verificar Supervisor si se proporciona
        Empleado supervisor = null;
        if (createDTO.getIdSupervisor() != null) {
            supervisor = empleadoRepository.findById(createDTO.getIdSupervisor())
                    .orElseThrow(() -> new RuntimeException(
                            "El Supervisor con ID " + createDTO.getIdSupervisor() + " no existe"));
        }

        Empleado empleado = empleadoMapper.fromCreateUpdateDTO(createDTO);

        // Asignar las entidades reales para asegurar consistencia
        empleado.setPuesto(puesto);
        empleado.setDepartamento(departamento);
        empleado.setSupervisor(supervisor);

        Empleado savedEmpleado = empleadoRepository.save(empleado);
        return empleadoMapper.toDTO(savedEmpleado);
    }

    public EmpleadoDTO update(Integer id, EmpleadoCreateDTO createDTO) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + id));

        // Validación de relaciones
        if (createDTO.getIdPuesto() != null) {
            if (!puestoRepository.existsById(createDTO.getIdPuesto())) {
                throw new RuntimeException("El Puesto con ID " + createDTO.getIdPuesto() + " no existe");
            }
        }
        if (createDTO.getIdDepartamento() != null) {
            if (!departamentoRepository.existsById(createDTO.getIdDepartamento())) {
                throw new RuntimeException("El Departamento con ID " + createDTO.getIdDepartamento() + " no existe");
            }
        }

        empleadoMapper.updateEntityFromDTO(createDTO, empleado);
        Empleado updatedEmpleado = empleadoRepository.save(empleado);
        return empleadoMapper.toDTO(updatedEmpleado);
    }

    public void delete(Integer id) {
        if (!empleadoRepository.existsById(id)) {
            throw new RuntimeException("Empleado no encontrado con ID: " + id);
        }
        empleadoRepository.deleteById(id);
    }
}
