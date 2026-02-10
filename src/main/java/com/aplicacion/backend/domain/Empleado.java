package com.aplicacion.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "empleados")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "apellido", length = 50, nullable = false)
    private String apellido;

    @Column(name = "dni_nie", length = 20, unique = true, nullable = false)
    private String dniNie;

    @Column(name = "email_corp", length = 100)
    private String emailCorp;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "fecha_contratacion")
    private LocalDate fechaContratacion;

    @ManyToOne
    @JoinColumn(name = "id_puesto")
    private Puesto puesto;

    @ManyToOne
    @JoinColumn(name = "id_departamento")
    @ToString.Exclude // Prevent circular reference in toString
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "id_supervisor")
    @ToString.Exclude // Prevent circular reference
    private Empleado supervisor;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoEmpleado estado;

    public enum EstadoEmpleado {
        ACTIVO,
        INACTIVO,
        BAJA_TEMPORAL
    }
}
