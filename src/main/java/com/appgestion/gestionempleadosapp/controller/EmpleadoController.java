package com.appgestion.gestionempleadosapp.controller;

import com.appgestion.gestionempleadosapp.dto.EmpleadoDTO;
import com.appgestion.gestionempleadosapp.service.EmpleadoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employee")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    /**
     *
     * @param empleadoService
     */
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    /**
     *
     * @return
     */
    @GetMapping
    public List<EmpleadoDTO> obtenerEmpleados() {
        return empleadoService.obtenerEmpleados();
    }

    /**
     *
     * @param departamentoId
     * @param empleadoDTO
     * @return
     */
    @PostMapping("/create/{departmentId}")
    public EmpleadoDTO crearEmpleado(@PathVariable(value = "departmentId") Long departamentoId, @RequestBody EmpleadoDTO empleadoDTO) {
        return empleadoService.crearEmpleado(departamentoId, empleadoDTO);
    }

    /**
     *
     * @param empleadoId
     */
    @DeleteMapping("/delete/{employeeId}")
    public void eliminarEmpleado(@PathVariable(value = "employeeId") Long empleadoId) {
        this.empleadoService.inactivarEmpleado(empleadoId);
    }

    @GetMapping("/highestSalary")
    public EmpleadoDTO obtenerSalarioMasAlto() {
        return empleadoService.obtenerEmpleadoSalarioMasAlto();
    }

    @GetMapping("/lowerAge")
    public EmpleadoDTO obtenerEdadMasBajo() {
        return empleadoService.obtenerEmpleadoMenorEdad();
    }

    @GetMapping("/countLastMonth")
    public long obtenerNumeroEmpleadosUltimoMes() {
        return empleadoService.contarEmpleadosUltimoMes();
    }
}
