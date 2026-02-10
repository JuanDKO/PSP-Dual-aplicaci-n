package com.aplicacion.backend.mapper;

import com.aplicacion.backend.domain.Asistencia;
import com.aplicacion.backend.dtos.asistencia.AsistenciaCreateDTO;
import com.aplicacion.backend.dtos.asistencia.AsistenciaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AsistenciaMapper {

    // Método 1: Mapeo de lectura (Entity a DTO)
    @Mapping(source = "empleado.idEmpleado", target = "idEmpleado")
    AsistenciaDTO toDTO(Asistencia asistencia);

    // Método 2: Mapeo de creación (DTO a Entity)
    @Mapping(source = "idEmpleado", target = "empleado.idEmpleado")
    Asistencia fromCreateUpdateDTO(AsistenciaCreateDTO dto);

    // Método 3: Mapeo de actualización (DTO a Entity EXISTENTE).
    @Mapping(source = "idEmpleado", target = "empleado.idEmpleado")
    void updateEntityFromDTO(AsistenciaCreateDTO dto, @MappingTarget Asistencia asistencia);

    // Método 4: Para mapear listas completas
    List<AsistenciaDTO> toDTO(List<Asistencia> asistencias);
}
