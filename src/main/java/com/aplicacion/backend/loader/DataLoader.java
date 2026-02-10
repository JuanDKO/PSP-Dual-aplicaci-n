package com.aplicacion.backend.loader;

import com.aplicacion.backend.domain.Asistencia;
import com.aplicacion.backend.domain.Departamento;
import com.aplicacion.backend.domain.Empleado;
import com.aplicacion.backend.domain.Empleado.EstadoEmpleado;
import com.aplicacion.backend.domain.HistorialSalario;
import com.aplicacion.backend.domain.Puesto;
import com.aplicacion.backend.repository.AsistenciaRepository;
import com.aplicacion.backend.repository.DepartamentoRepository;
import com.aplicacion.backend.repository.EmpleadoRepository;
import com.aplicacion.backend.repository.HistorialSalarioRepository;
import com.aplicacion.backend.repository.PuestoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final PuestoRepository puestoRepository;
    private final DepartamentoRepository departamentoRepository;
    private final EmpleadoRepository empleadoRepository;
    private final HistorialSalarioRepository historialSalarioRepository;
    private final AsistenciaRepository asistenciaRepository;

    @Override
    public void run(String... args) throws Exception {
        if (puestoRepository.count() > 0) {
            return; // Ya hay datos, no cargar nada
        }

        // 1. Crear Puestos
        List<Puesto> puestos = new ArrayList<>();
        puestos.add(Puesto.builder().tituloPuesto("Desarrollador Junior").salarioMin(new BigDecimal("20000"))
                .salarioMax(new BigDecimal("30000")).build());
        puestos.add(Puesto.builder().tituloPuesto("Desarrollador Senior").salarioMin(new BigDecimal("35000"))
                .salarioMax(new BigDecimal("60000")).build());
        puestos.add(Puesto.builder().tituloPuesto("Gerente de Proyecto").salarioMin(new BigDecimal("45000"))
                .salarioMax(new BigDecimal("70000")).build());
        puestos.add(Puesto.builder().tituloPuesto("Analista de Datos").salarioMin(new BigDecimal("25000"))
                .salarioMax(new BigDecimal("40000")).build());
        puestos.add(Puesto.builder().tituloPuesto("Especialista en RRHH").salarioMin(new BigDecimal("22000"))
                .salarioMax(new BigDecimal("35000")).build());
        puestoRepository.saveAll(puestos);

        // 2. Crear Departamentos (sin gerente inicialmente)
        List<Departamento> departamentos = new ArrayList<>();
        departamentos.add(Departamento.builder().nombre("IT").build());
        departamentos.add(Departamento.builder().nombre("Recursos Humanos").build());
        departamentos.add(Departamento.builder().nombre("Finanzas").build());
        departamentos.add(Departamento.builder().nombre("Marketing").build());
        departamentos.add(Departamento.builder().nombre("Operaciones").build());
        departamentoRepository.saveAll(departamentos);

        // 3. Crear Empleados
        List<Empleado> empleados = new ArrayList<>();

        // Empleado 1: IT - Senior Dev
        empleados.add(Empleado.builder()
                .nombre("Juan")
                .apellido("Pérez")
                .dniNie("12345678A")
                .emailCorp("juan.perez@empresa.com")
                .telefono("600111222")
                .fechaContratacion(LocalDate.now().minusYears(2))
                .puesto(puestos.get(1))
                .departamento(departamentos.get(0))
                .estado(EstadoEmpleado.ACTIVO)
                .build());

        // Empleado 2: IT - Junior Dev (Supervisor: Juan)
        empleados.add(Empleado.builder()
                .nombre("Ana")
                .apellido("García")
                .dniNie("87654321B")
                .emailCorp("ana.garcia@empresa.com")
                .telefono("600333444")
                .fechaContratacion(LocalDate.now().minusMonths(6))
                .puesto(puestos.get(0))
                .departamento(departamentos.get(0))
                .estado(EstadoEmpleado.ACTIVO)
                .build());

        // Empleado 3: RRHH - Especialista
        empleados.add(Empleado.builder()
                .nombre("Carlos")
                .apellido("López")
                .dniNie("11223344C")
                .emailCorp("carlos.lopez@empresa.com")
                .telefono("600555666")
                .fechaContratacion(LocalDate.now().minusYears(1))
                .puesto(puestos.get(4))
                .departamento(departamentos.get(1))
                .estado(EstadoEmpleado.ACTIVO)
                .build());

        // Empleado 4: Finanzas - Analista
        empleados.add(Empleado.builder()
                .nombre("Laura")
                .apellido("Martínez")
                .dniNie("55667788D")
                .emailCorp("laura.martinez@empresa.com")
                .telefono("600777888")
                .fechaContratacion(LocalDate.now().minusYears(3))
                .puesto(puestos.get(3))
                .departamento(departamentos.get(2))
                .estado(EstadoEmpleado.ACTIVO)
                .build());

        // Empleado 5: Gerente General (Supervisor de Juan, Carlos, Laura)
        empleados.add(Empleado.builder()
                .nombre("Roberto")
                .apellido("Sánchez")
                .dniNie("99887766E")
                .emailCorp("roberto.sanchez@empresa.com")
                .telefono("600999000")
                .fechaContratacion(LocalDate.now().minusYears(5))
                .puesto(puestos.get(2))
                .departamento(departamentos.get(4))
                .estado(EstadoEmpleado.ACTIVO)
                .build());

        empleadoRepository.saveAll(empleados);

        // Actualizar supervisores (guardar de nuevo)
        Empleado juan = empleados.get(0);
        Empleado ana = empleados.get(1);
        Empleado roberto = empleados.get(4); // Gerente

        ana.setSupervisor(juan); // Ana reporta a Juan
        juan.setSupervisor(roberto); // Juan reporta a Roberto
        empleados.get(2).setSupervisor(roberto); // Carlos reporta a Roberto
        empleados.get(3).setSupervisor(roberto); // Laura reporta a Roberto

        empleadoRepository.saveAll(empleados);

        // 4. Actualizar Departamentos con Gerentes
        departamentos.get(0).setGerente(juan); // Juan gerente IT
        departamentos.get(1).setGerente(empleados.get(2)); // Carlos gerente RRHH
        departamentos.get(4).setGerente(roberto); // Roberto gerente Operaciones
        departamentoRepository.saveAll(departamentos);

        // 5. Crear Historial Salarios
        List<HistorialSalario> historiales = new ArrayList<>();
        // Juan History
        historiales.add(HistorialSalario.builder().empleado(juan).salarioMensual(new BigDecimal("35000"))
                .fechaDesde(LocalDate.now().minusYears(2)).fechaHasta(LocalDate.now().minusYears(1)).build());
        historiales.add(HistorialSalario.builder().empleado(juan).salarioMensual(new BigDecimal("38000"))
                .fechaDesde(LocalDate.now().minusYears(1)).build());
        // Ana History
        historiales.add(HistorialSalario.builder().empleado(ana).salarioMensual(new BigDecimal("22000"))
                .fechaDesde(LocalDate.now().minusMonths(6)).build());
        // Roberto History
        historiales.add(HistorialSalario.builder().empleado(roberto).salarioMensual(new BigDecimal("50000"))
                .fechaDesde(LocalDate.now().minusYears(5)).build());
        // Carlos History
        historiales.add(HistorialSalario.builder().empleado(empleados.get(2)).salarioMensual(new BigDecimal("25000"))
                .fechaDesde(LocalDate.now().minusYears(1)).build());

        historialSalarioRepository.saveAll(historiales);

        // 6. Crear Asistencias
        List<Asistencia> asistencias = new ArrayList<>();
        LocalDate hoy = LocalDate.now();
        // Asistencias de hoy
        asistencias.add(Asistencia.builder().empleado(juan).fecha(hoy).horaEntrada(LocalTime.of(8, 0))
                .horaSalida(LocalTime.of(17, 0)).build());
        asistencias.add(Asistencia.builder().empleado(ana).fecha(hoy).horaEntrada(LocalTime.of(9, 0))
                .horaSalida(LocalTime.of(18, 0)).build());
        asistencias.add(Asistencia.builder().empleado(empleados.get(2)).fecha(hoy).horaEntrada(LocalTime.of(8, 30))
                .horaSalida(LocalTime.of(16, 30)).build());
        asistencias.add(Asistencia.builder().empleado(empleados.get(3)).fecha(hoy).horaEntrada(LocalTime.of(9, 15))
                .horaSalida(LocalTime.of(18, 15)).build());
        // Asistencia de ayer (Roberto)
        asistencias.add(Asistencia.builder().empleado(roberto).fecha(hoy.minusDays(1)).horaEntrada(LocalTime.of(10, 0))
                .horaSalida(LocalTime.of(19, 0)).build());

        asistenciaRepository.saveAll(asistencias);
    }
}
