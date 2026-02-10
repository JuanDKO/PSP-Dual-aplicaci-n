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
public class EmpleadoCreateDTO {
    @jakarta.validation.constraints.NotBlank(message = "El nombre es obligatorio")
    @jakarta.validation.constraints.Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    private String nombre;

    @jakarta.validation.constraints.NotBlank(message = "El apellido es obligatorio")
    @jakarta.validation.constraints.Size(max = 50, message = "El apellido no puede tener más de 50 caracteres")
    private String apellido;

    @jakarta.validation.constraints.NotBlank(message = "El DNI/NIE es obligatorio")
    @jakarta.validation.constraints.Size(max = 20, message = "El DNI/NIE no puede tener más de 20 caracteres")
    private String dniNie;

    @jakarta.validation.constraints.Email(message = "Debe ser un email válido")
    @jakarta.validation.constraints.Size(max = 100, message = "El email no puede tener más de 100 caracteres")
    private String emailCorp;

    @jakarta.validation.constraints.Size(max = 20, message = "El teléfono no puede tener más de 20 caracteres")
    private String telefono;

    private LocalDate fechaContratacion;
    private Integer idPuesto;
    private Integer idDepartamento;
    private Integer idSupervisor;
    private EstadoEmpleado estado;
}
