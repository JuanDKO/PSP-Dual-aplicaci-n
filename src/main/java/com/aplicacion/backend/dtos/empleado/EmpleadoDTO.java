package com.aplicacion.backend.dtos.empleado;

import com.aplicacion.backend.domain.Empleado.EstadoEmpleado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpleadoDTO {
    private Integer idEmpleado;
    private String nombre;
    private String apellido;
    private String dniNie;
    private String emailCorp;
    private String telefono;
    private LocalDate fechaContratacion;
    private Integer idPuesto;
    private Integer idDepartamento;
    private Integer idSupervisor;
    private EstadoEmpleado estado;
}
