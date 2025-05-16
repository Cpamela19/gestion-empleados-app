package com.appgestion.gestionempleadosapp.config;

import com.appgestion.gestionempleadosapp.model.Departamento;
import com.appgestion.gestionempleadosapp.model.Empleado;
import com.appgestion.gestionempleadosapp.repository.DepartamentoRepository;
import com.appgestion.gestionempleadosapp.repository.EmpleadoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DataInitializer implements CommandLineRunner {
    private final EmpleadoRepository empleadoRepository;

    private final DepartamentoRepository departamentoRepository;

    public DataInitializer(EmpleadoRepository empleadoRepository, DepartamentoRepository departamentoRepository) {
        this.empleadoRepository = empleadoRepository;
        this.departamentoRepository = departamentoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Departamento departamento = new Departamento("Sistemas");
        Departamento departamento2 = new Departamento("Financiero");

        departamentoRepository.save(departamento);
        departamentoRepository.save(departamento2);

        Empleado empleado = new Empleado(
                "Juan",
                "Perez",
                30,
                "Desarrollador",
                5000,
                LocalDate.parse("01/01/2022", formatter),
                LocalDate.parse("01/01/2025", formatter)
        );
        empleado.setDepartamento(departamento);

        Empleado empleado2 = new Empleado(
                "Rosa",
                "Alvarez",
                30,
                "QA",
                5000,
                LocalDate.parse("01/01/2025", formatter),
                LocalDate.parse("01/01/2025", formatter)
        );
        empleado2.setDepartamento(departamento2);

        empleadoRepository.save(empleado);
        empleadoRepository.save(empleado2);

    }
}
