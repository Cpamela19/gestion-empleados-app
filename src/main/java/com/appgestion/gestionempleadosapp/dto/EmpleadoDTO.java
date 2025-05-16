package com.appgestion.gestionempleadosapp.dto;

import com.appgestion.gestionempleadosapp.model.Departamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class EmpleadoDTO {

    private Long id;

    private String nombre;

    private String apellido;

    private int edad;

    private String rol;

    private int salario;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaIngreso;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaSalida;

    private boolean estado = true;

    private Departamento departamento;

    public EmpleadoDTO(Long id, String nombre, String apellido, int edad, String rol, int salario, LocalDate fechaIngreso, LocalDate fechaSalida, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.rol = rol;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.estado = estado;
    }

    public EmpleadoDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
