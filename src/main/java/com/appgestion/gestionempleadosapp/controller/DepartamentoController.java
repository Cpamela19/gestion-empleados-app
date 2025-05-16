package com.appgestion.gestionempleadosapp.controller;

import com.appgestion.gestionempleadosapp.dto.DepartamentoDTO;
import com.appgestion.gestionempleadosapp.service.DepartamentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/department")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @GetMapping
    public List<DepartamentoDTO> obtenerDepartamentos() {
        return departamentoService.obtenerDepartamentos();
    }

    @GetMapping("/{id}/hasNotEmployees")
    public boolean hasNotEmployees(@PathVariable Long id) {
        return departamentoService.hasNotEmployees(id);
    }

    @PostMapping("/create")
    public DepartamentoDTO crearDepartamento(@RequestBody DepartamentoDTO departamentoDTO) {
        return departamentoService.crearDepartamento(departamentoDTO);
    }

    @DeleteMapping("/delete/{deparmentId}")
    public void eliminarDepartamento(@PathVariable(value = "deparmentId") Long departmentId) {
         this.departamentoService.inactivarDepartamento(departmentId);
    }

}
