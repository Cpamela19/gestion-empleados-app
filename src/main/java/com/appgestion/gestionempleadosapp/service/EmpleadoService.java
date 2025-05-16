package com.appgestion.gestionempleadosapp.service;

import com.appgestion.gestionempleadosapp.dto.EmpleadoDTO;
import com.appgestion.gestionempleadosapp.mapper.EmpleadoMapper;
import com.appgestion.gestionempleadosapp.model.Departamento;
import com.appgestion.gestionempleadosapp.model.Empleado;
import com.appgestion.gestionempleadosapp.repository.DepartamentoRepository;
import com.appgestion.gestionempleadosapp.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {
    private final EmpleadoRepository empleadoRepository;

    private final DepartamentoRepository departamentoRepository;

    /**
     * @param empleadoRepository
     */
    public EmpleadoService(EmpleadoRepository empleadoRepository, DepartamentoRepository departamentoRepository) {
        this.empleadoRepository = empleadoRepository;
        this.departamentoRepository = departamentoRepository;
    }

    /**
     *
     * @return
     */
    public List<EmpleadoDTO> obtenerEmpleados() {
        return empleadoRepository.findAll().stream()
                .map(EmpleadoMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param departamentoId
     * @param empleadoDTO
     * @return
     */
    public EmpleadoDTO crearEmpleado(Long departamentoId, EmpleadoDTO empleadoDTO) {
        Departamento departamento = departamentoRepository.findById(departamentoId)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));

        Empleado empleado = EmpleadoMapper.toEntity(empleadoDTO);

        empleado.setDepartamento(departamento);

        Empleado saved = empleadoRepository.save(empleado);
        return EmpleadoMapper.toDTO(saved);
    }

    /**
     *
     * @param empleadoId
     */
    public void inactivarEmpleado(Long empleadoId) {
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        empleado.setEstado(false);

        empleadoRepository.save(empleado);
    }

    /**
     *
     * @return
     */
    public EmpleadoDTO obtenerEmpleadoSalarioMasAlto() {
        Optional<Empleado> empleado = empleadoRepository.findAll()
                .stream()
                .filter(Empleado::isEstado)
                .max(Comparator.comparingDouble(Empleado::getSalario));

        return empleado.map(EmpleadoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("No hay empleados activos"));
    }

    /**
     *
     * @return
     */
    public EmpleadoDTO obtenerEmpleadoMenorEdad() {
        Optional<Empleado> empleado = empleadoRepository.findAll()
                .stream()
                .filter(Empleado::isEstado)
                .min(Comparator.comparingInt(Empleado::getEdad));

        return empleado.map(EmpleadoMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("No hay empleados activos"));
    }

    /**
     *
     * @return
     */
    public long contarEmpleadosUltimoMes() {
        LocalDate haceUnMes = LocalDate.now().minusMonths(1);
        return empleadoRepository.findAll()
                .stream()
                .filter(Empleado::isEstado)
                .filter(e -> e.getFechaIngreso() != null && !e.getFechaIngreso().isBefore(haceUnMes))
                .count();
    }
}
