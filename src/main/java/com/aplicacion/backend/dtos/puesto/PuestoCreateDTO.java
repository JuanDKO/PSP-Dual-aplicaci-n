package com.aplicacion.backend.dtos.puesto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PuestoCreateDTO {
    @jakarta.validation.constraints.NotBlank(message = "El título del puesto es obligatorio")
    @jakarta.validation.constraints.Size(max = 50, message = "El título no puede tener más de 50 caracteres")
    private String tituloPuesto;

    @jakarta.validation.constraints.NotNull(message = "El salario mínimo es obligatorio")
    @jakarta.validation.constraints.PositiveOrZero(message = "El salario mínimo debe ser positivo o cero")
    private BigDecimal salarioMin;

    @jakarta.validation.constraints.NotNull(message = "El salario máximo es obligatorio")
    @jakarta.validation.constraints.PositiveOrZero(message = "El salario máximo debe ser positivo o cero")
    private BigDecimal salarioMax;
}
