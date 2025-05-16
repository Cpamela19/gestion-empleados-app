package com.appgestion.gestionempleadosapp.repository;

import com.appgestion.gestionempleadosapp.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    /**
     * @param empleadoId
     * @return
     */
    Optional<Empleado> findById(Long empleadoId);

}
