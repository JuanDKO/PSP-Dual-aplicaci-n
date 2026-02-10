package com.aplicacion.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "puestos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Puesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_puesto")
    private Integer idPuesto;

    @Column(name = "titulo_puesto", length = 100, nullable = false)
    private String tituloPuesto;

    @Column(name = "salario_min", precision = 10, scale = 2)
    private BigDecimal salarioMin;

    @Column(name = "salario_max", precision = 10, scale = 2)
    private BigDecimal salarioMax;
}
