package com.aplicacion.backend.dtos.asistencia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsistenciaCreateDTO {
    @jakarta.validation.constraints.NotNull(message = "El ID del empleado es obligatorio")
    private Integer idEmpleado;

    @jakarta.validation.constraints.NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @jakarta.validation.constraints.NotNull(message = "La hora de entrada es obligatoria")
    private LocalTime horaEntrada;

    private LocalTime horaSalida;
}
