package com.aplicacion.backend.mapper;

import com.aplicacion.backend.domain.Departamento;
import com.aplicacion.backend.dtos.departamento.DepartamentoCreateDTO;
import com.aplicacion.backend.dtos.departamento.DepartamentoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartamentoMapper {

    // Método 1: Mapeo de lectura (Entity a DTO)
    @Mapping(source = "gerente.idEmpleado", target = "idGerente")
    DepartamentoDTO toDTO(Departamento departamento);

    // Método 2: Mapeo de creación (DTO a Entity)
    @Mapping(source = "idGerente", target = "gerente.idEmpleado")
    Departamento fromCreateUpdateDTO(DepartamentoCreateDTO dto);

    // Método 3: Mapeo de actualización (DTO a Entity EXISTENTE).
    @Mapping(source = "idGerente", target = "gerente.idEmpleado")
    void updateEntityFromDTO(DepartamentoCreateDTO dto, @MappingTarget Departamento departamento);

    // Método 4: Para mapear listas completas
    List<DepartamentoDTO> toDTO(List<Departamento> departamentos);
}
