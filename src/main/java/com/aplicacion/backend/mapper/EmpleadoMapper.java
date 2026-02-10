package com.aplicacion.backend.mapper;

import com.aplicacion.backend.domain.Empleado;
import com.aplicacion.backend.dtos.empleado.EmpleadoCreateDTO;
import com.aplicacion.backend.dtos.empleado.EmpleadoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {

    // Método 1: Mapeo de lectura (Entity a DTO)
    @Mapping(source = "puesto.idPuesto", target = "idPuesto")
    @Mapping(source = "departamento.idDepartamento", target = "idDepartamento")
    @Mapping(source = "supervisor.idEmpleado", target = "idSupervisor")
    EmpleadoDTO toDTO(Empleado empleado);

    // Método 2: Mapeo de creación (DTO a Entity)
    @Mapping(source = "idPuesto", target = "puesto.idPuesto")
    @Mapping(source = "idDepartamento", target = "departamento.idDepartamento")
    @Mapping(source = "idSupervisor", target = "supervisor.idEmpleado")
    Empleado fromCreateUpdateDTO(EmpleadoCreateDTO dto);

    // Método 3: Mapeo de actualización (DTO a Entity EXISTENTE).
    @Mapping(source = "idPuesto", target = "puesto.idPuesto")
    @Mapping(source = "idDepartamento", target = "departamento.idDepartamento")
    @Mapping(source = "idSupervisor", target = "supervisor.idEmpleado")
    void updateEntityFromDTO(EmpleadoCreateDTO dto, @MappingTarget Empleado empleado);

    // Método 4: Para mapear listas completas
    List<EmpleadoDTO> toDTO(List<Empleado> empleados);
}
