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
public class HistorialSalarioDTO {
    private Integer idHistorial;
    private Integer idEmpleado;
    private BigDecimal salarioMensual;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
}
