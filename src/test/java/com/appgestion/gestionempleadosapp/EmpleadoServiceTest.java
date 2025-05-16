package com.appgestion.gestionempleadosapp;

import com.appgestion.gestionempleadosapp.dto.EmpleadoDTO;
import com.appgestion.gestionempleadosapp.mapper.EmpleadoMapper;
import com.appgestion.gestionempleadosapp.model.Departamento;
import com.appgestion.gestionempleadosapp.model.Empleado;
import com.appgestion.gestionempleadosapp.repository.DepartamentoRepository;
import com.appgestion.gestionempleadosapp.repository.EmpleadoRepository;
import com.appgestion.gestionempleadosapp.service.EmpleadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EmpleadoServiceTest {
    private EmpleadoRepository empleadoRepository;
    private DepartamentoRepository departamentoRepository;
    private EmpleadoService empleadoService;

    @BeforeEach
    void setUp() {
        empleadoRepository = mock(EmpleadoRepository.class);

        departamentoRepository = mock(DepartamentoRepository.class);

        empleadoService = new EmpleadoService(empleadoRepository, departamentoRepository);
    }

    @Test
    void testObtenerEmpleados() {
        Empleado empleado = new Empleado();
        empleado.setId(1L);
        empleado.setNombre("Juan");
        empleado.setEstado(true);

        when(empleadoRepository.findAll()).thenReturn(List.of(empleado));

        List<EmpleadoDTO> empleados = empleadoService.obtenerEmpleados();

        assertEquals(1, empleados.size());
        assertEquals("Juan", empleados.get(0).getNombre());
    }

    @Test
    void testCrearEmpleado() {
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setNombre("Ana");

        Departamento depto = new Departamento();
        depto.setId(1L);
        depto.setNombre("IT");

        Empleado empleado = EmpleadoMapper.toEntity(dto);
        empleado.setDepartamento(depto);

        when(departamentoRepository.findById(1L)).thenReturn(Optional.of(depto));
        when(empleadoRepository.save(any(Empleado.class))).thenAnswer(i -> i.getArguments()[0]);

        EmpleadoDTO creado = empleadoService.crearEmpleado(1L, dto);

        assertEquals("Ana", creado.getNombre());
    }

    @Test
    void testInactivarEmpleado() {
        Empleado empleado = new Empleado();
        empleado.setId(1L);
        empleado.setEstado(true);

        when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));

        empleadoService.inactivarEmpleado(1L);

        assertFalse(empleado.isEstado());
        verify(empleadoRepository).save(empleado);
    }

    @Test
    void testObtenerEmpleadoSalarioMasAlto() {
        Empleado e1 = new Empleado();
        e1.setNombre("Luis");
        e1.setSalario(5000);
        e1.setEstado(true);

        Empleado e2 = new Empleado();
        e2.setNombre("Carlos");
        e2.setSalario(7000);
        e2.setEstado(true);

        when(empleadoRepository.findAll()).thenReturn(Arrays.asList(e1, e2));

        EmpleadoDTO dto = empleadoService.obtenerEmpleadoSalarioMasAlto();

        assertEquals("Carlos", dto.getNombre());
    }

    @Test
    void testObtenerEmpleadoMenorEdad() {
        Empleado e1 = new Empleado();
        e1.setNombre("María");
        e1.setEdad(30);
        e1.setEstado(true);

        Empleado e2 = new Empleado();
        e2.setNombre("Pedro");
        e2.setEdad(25);
        e2.setEstado(true);

        when(empleadoRepository.findAll()).thenReturn(Arrays.asList(e1, e2));

        EmpleadoDTO dto = empleadoService.obtenerEmpleadoMenorEdad();

        assertEquals("Pedro", dto.getNombre());
    }

    @Test
    void testContarEmpleadosUltimoMes() {
        Empleado e1 = new Empleado();
        e1.setEstado(true);
        e1.setFechaIngreso(LocalDate.now().minusDays(10));

        Empleado e2 = new Empleado();
        e2.setEstado(true);
        e2.setFechaIngreso(LocalDate.now().minusMonths(2));

        when(empleadoRepository.findAll()).thenReturn(Arrays.asList(e1, e2));

        long count = empleadoService.contarEmpleadosUltimoMes();

        assertEquals(1, count);
    }
}
