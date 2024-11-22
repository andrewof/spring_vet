package com.andres.veterinaria.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@org.hibernate.annotations.Immutable
@Table(name = "vw_mascotas_duenos") // Aquí especificamos el nombre de la vista
public class MascotaDuenoVista {

    @Id
    @Column(name = "id_mascota")
    private Long idMascota;

    @Column(name = "Nombre_mascota")
    private String nombreMascota;

    @Column(name = "Tipo")
    private String tipo;

    @Column(name = "Raza")
    private String raza;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "Nombre")
    private String nombreDueno;

    @Column(name = "Apellido")
    private String apellidoDueno;

    @Column(name = "Telefono")
    private String telefonoDueno;

    public Long getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Long idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombreDueno() {
        return nombreDueno;
    }

    public void setNombreDueno(String nombreDueno) {
        this.nombreDueno = nombreDueno;
    }

    public String getApellidoDueno() {
        return apellidoDueno;
    }

    public void setApellidoDueno(String apellidoDueno) {
        this.apellidoDueno = apellidoDueno;
    }

    public String getTelefonoDueno() {
        return telefonoDueno;
    }

    public void setTelefonoDueno(String telefonoDueno) {
        this.telefonoDueno = telefonoDueno;
    }
}

