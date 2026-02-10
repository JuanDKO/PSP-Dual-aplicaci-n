package com.aplicacion.backend.dtos.departamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartamentoCreateDTO {
    @jakarta.validation.constraints.NotBlank(message = "El nombre del departamento es obligatorio")
    @jakarta.validation.constraints.Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    private String nombre;

    private Integer idGerente;
}
