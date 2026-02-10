package com.aplicacion.backend.dtos.historial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialSalarioCreateDTO {
    @jakarta.validation.constraints.NotNull(message = "El ID del empleado es obligatorio")
    private Integer idEmpleado;

    @jakarta.validation.constraints.NotNull(message = "El salario mensual es obligatorio")
    @jakarta.validation.constraints.Positive(message = "El salario debe ser positivo")
    private BigDecimal salarioMensual;

    @jakarta.validation.constraints.NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaDesde;

    private LocalDate fechaHasta;
}
