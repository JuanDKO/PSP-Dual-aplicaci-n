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
public class PuestoDTO {
    private Integer idPuesto;
    private String tituloPuesto;
    private BigDecimal salarioMin;
    private BigDecimal salarioMax;
}
