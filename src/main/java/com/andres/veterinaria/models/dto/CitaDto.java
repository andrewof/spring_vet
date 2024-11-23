package com.andres.veterinaria.models.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class CitaDto {

    private Long idCita;
    private Long idCliente;
    private Long idMascota;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;

    public CitaDto() {
    }

    public CitaDto(Long idCita, Long idCliente, Long idMascota, LocalDate fecha, LocalTime hora, String estado) {
        this.idCita = idCita;
        this.idCliente = idCliente;
        this.idMascota = idMascota;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

