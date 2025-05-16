package com.appgestion.gestionempleadosapp.dto;

public class DepartamentoDTO {

    private Long id;

    private String nombre;

    private boolean estado = true;

    public DepartamentoDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public DepartamentoDTO() {

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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
