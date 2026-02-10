package com.aplicacion.backend.dtos.departamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartamentoDTO {
    private Integer idDepartamento;
    private String nombre;
    private Integer idGerente;
}
