package com.appgestion.gestionempleadosapp.repository;

import com.appgestion.gestionempleadosapp.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    Optional<Departamento> findById(Long departamentoId);
}
