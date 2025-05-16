package com.appgestion.gestionempleadosapp.mapper;

import com.appgestion.gestionempleadosapp.dto.EmpleadoDTO;
import com.appgestion.gestionempleadosapp.model.Empleado;

public class EmpleadoMapper {

    public static Empleado toEntity(EmpleadoDTO dto) {
        Empleado empleado = new Empleado();
        empleado.setId(dto.getId());
        empleado.setNombre(dto.getNombre());
        empleado.setApellido(dto.getApellido());
        empleado.setEdad(dto.getEdad());
        empleado.setRol(dto.getRol());
        empleado.setSalario(dto.getSalario());
        empleado.setFechaIngreso(dto.getFechaIngreso());
        empleado.setFechaSalida(dto.getFechaSalida());
        empleado.setEstado(dto.isEstado());
        empleado.setDepartamento(dto.getDepartamento());
        return empleado;
    }

    public static EmpleadoDTO toDTO(Empleado entity) {
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setEdad(entity.getEdad());
        dto.setRol(entity.getRol());
        dto.setSalario(entity.getSalario());
        dto.setFechaIngreso(entity.getFechaIngreso());
        dto.setFechaSalida(entity.getFechaSalida());
        dto.setEstado(entity.isEstado());
        dto.setDepartamento(entity.getDepartamento());
        return dto;
    }
}
