package com.aplicacion.backend.mapper;

import com.aplicacion.backend.domain.HistorialSalario;
import com.aplicacion.backend.dtos.historial.HistorialSalarioCreateDTO;
import com.aplicacion.backend.dtos.historial.HistorialSalarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HistorialSalarioMapper {

    // Método 1: Mapeo de lectura (Entity a DTO)
    @Mapping(source = "empleado.idEmpleado", target = "idEmpleado")
    HistorialSalarioDTO toDTO(HistorialSalario historialSalario);

    // Método 2: Mapeo de creación (DTO a Entity)
    @Mapping(source = "idEmpleado", target = "empleado.idEmpleado")
    HistorialSalario fromCreateUpdateDTO(HistorialSalarioCreateDTO dto);

    // Método 3: Mapeo de actualización (DTO a Entity EXISTENTE).
    @Mapping(source = "idEmpleado", target = "empleado.idEmpleado")
    void updateEntityFromDTO(HistorialSalarioCreateDTO dto, @MappingTarget HistorialSalario historialSalario);

    // Método 4: Para mapear listas completas
    List<HistorialSalarioDTO> toDTO(List<HistorialSalario> historialSalarios);
}
