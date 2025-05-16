package com.appgestion.gestionempleadosapp.service;

import com.appgestion.gestionempleadosapp.dto.DepartamentoDTO;
import com.appgestion.gestionempleadosapp.mapper.DepartamentoMapper;
import com.appgestion.gestionempleadosapp.model.Departamento;
import com.appgestion.gestionempleadosapp.repository.DepartamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartamentoService {
    private final DepartamentoRepository departamentoRepository;

    private final EmpleadoService empleadoService;

    /**
     *
     * @param departamentoRepository
     */
    public DepartamentoService(DepartamentoRepository departamentoRepository, EmpleadoService empleadoService) {
        this.departamentoRepository = departamentoRepository;
        this.empleadoService = empleadoService;
    }

    /**
     *
     * @return
     */
    public List<DepartamentoDTO> obtenerDepartamentos() {
        return departamentoRepository.findAll().stream()
                .map(DepartamentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param departamentoDTO
     * @return
     */
    public DepartamentoDTO crearDepartamento(DepartamentoDTO departamentoDTO) {
        Departamento departamento = DepartamentoMapper.toEntity(departamentoDTO);
        Departamento guardado = departamentoRepository.save(departamento);
        return DepartamentoMapper.toDTO(guardado);
    }

    /**
     *
     * @param departamentoId
     */
    public void inactivarDepartamento(Long departamentoId) {
        Departamento departamento = departamentoRepository.findById(departamentoId)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));

        departamento.setEstado(false);

        departamentoRepository.save(departamento);
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean hasNotEmployees(@PathVariable Long id) {
        Departamento dpto = departamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));

        return empleadoService.obtenerEmpleados()
                .stream()
                .filter(emp -> emp.getDepartamento() != null && emp.getDepartamento().getId().equals(dpto.getId()))
                .allMatch(emp -> !emp.isEstado());
    }
}
