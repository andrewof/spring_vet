package com.andres.veterinaria.models.dto;

import java.time.LocalDateTime;

public class CitaDto {

    private Long idCita;
    private Long idCliente;
    private Long idMascota;
    private LocalDateTime fechaHora;
    private String estado;

    public CitaDto() {
    }

    // Constructor con parámetros
    public CitaDto(Long idCita, Long idCliente, Long idMascota, LocalDateTime fechaHora, String estado) {
        this.idCita = idCita;
        this.idCliente = idCliente;
        this.idMascota = idMascota;
        this.fechaHora = fechaHora;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getIdCita() {
        return idCita;
    }

    public void setIdCita(Long idCita) {
        this.idCita = idCita;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Long idMascota) {
        this.idMascota = idMascota;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

