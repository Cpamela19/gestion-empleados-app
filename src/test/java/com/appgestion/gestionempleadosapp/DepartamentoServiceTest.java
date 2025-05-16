package com.appgestion.gestionempleadosapp;

import com.appgestion.gestionempleadosapp.dto.DepartamentoDTO;
import com.appgestion.gestionempleadosapp.model.Departamento;
import com.appgestion.gestionempleadosapp.repository.DepartamentoRepository;
import com.appgestion.gestionempleadosapp.repository.EmpleadoRepository;
import com.appgestion.gestionempleadosapp.service.DepartamentoService;
import com.appgestion.gestionempleadosapp.service.EmpleadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DepartamentoServiceTest {

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoService empleadoService;

    @BeforeEach
    void setUp() {
        empleadoRepository.deleteAll(); // elimina primero los empleados
        departamentoRepository.deleteAll(); // luego los departamentos
    }

    @Test
    void testCrearYObtenerDepartamentos() {
        DepartamentoDTO nuevo = new DepartamentoDTO();
        nuevo.setNombre("Marketing");
        nuevo.setEstado(true);

        departamentoService.crearDepartamento(nuevo);

        List<DepartamentoDTO> lista = departamentoService.obtenerDepartamentos();
        assertEquals(1, lista.size());
        assertEquals("Marketing", lista.get(0).getNombre());
    }

    @Test
    void testInactivarDepartamento() {
        Departamento dpto = new Departamento("RRHH");
        dpto.setEstado(true);
        Departamento guardado = departamentoRepository.save(dpto);

        departamentoService.inactivarDepartamento(guardado.getId());

        Departamento actualizado = departamentoRepository.findById(guardado.getId()).orElseThrow();
        assertFalse(actualizado.isEstado());
    }

    @Test
    void testHasNotEmployees_sinEmpleados() {
        Departamento dpto = new Departamento("TI");
        dpto.setEstado(true);
        Departamento guardado = departamentoRepository.save(dpto);

        boolean result = departamentoService.hasNotEmployees(guardado.getId());
        assertTrue(result); // no hay empleados, por lo tanto, no tiene empleados activos
    }
}
