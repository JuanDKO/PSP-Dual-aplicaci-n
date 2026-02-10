package com.aplicacion.backend.mapper;

import com.aplicacion.backend.domain.Puesto;
import com.aplicacion.backend.dtos.puesto.PuestoCreateDTO;
import com.aplicacion.backend.dtos.puesto.PuestoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PuestoMapper {

    // Método 1: Mapeo de lectura (Entity a DTO)
    PuestoDTO toDTO(Puesto puesto);

    // Método 2: Mapeo de creación (DTO a Entity)
    Puesto fromCreateUpdateDTO(PuestoCreateDTO dto);

    // Método 3: Mapeo de actualización (DTO a Entity EXISTENTE).
    void updateEntityFromDTO(PuestoCreateDTO dto, @MappingTarget Puesto puesto);

    // Método 4: Para mapear listas completas
    List<PuestoDTO> toDTO(List<Puesto> puestos);
}
