package com.appgestion.gestionempleadosapp.mapper;

import com.appgestion.gestionempleadosapp.dto.DepartamentoDTO;
import com.appgestion.gestionempleadosapp.model.Departamento;

public class DepartamentoMapper {

    public static Departamento toEntity(DepartamentoDTO dto) {
        Departamento departamento = new Departamento();
        departamento.setId(dto.getId());
        departamento.setNombre(dto.getNombre());
        departamento.setEstado(dto.isEstado());
        return departamento;
    }

    public static DepartamentoDTO toDTO(Departamento departamento) {
        DepartamentoDTO dto = new DepartamentoDTO();
        dto.setId(departamento.getId());
        dto.setNombre(departamento.getNombre());
        dto.setEstado(departamento.isEstado());
        return dto;
    }
}
