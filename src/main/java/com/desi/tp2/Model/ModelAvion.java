package com.desi.tp2.Model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class ModelAvion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAvion;
	
    private String modelo;

    private int filas;

    private int asientosXFila;

    private String aerolinea;  
    
    public ModelAvion() {

    }
    
    public ModelAvion(String modelo, int filas, int asientosXFila, String aerolinea) {
        this.modelo = modelo;
        this.filas = filas;
        this.asientosXFila = asientosXFila;
        this.aerolinea = aerolinea;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getAsientosXFila() {
        return asientosXFila;
    }

    public void setAsientosXFila(int asientosXFila) {
        this.asientosXFila = asientosXFila;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelAvion anotherObject)) return false;
        return getIdAvion() == anotherObject.getIdAvion();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdAvion(), getModelo(), getFilas(), getAsientosXFila(), getAerolinea());
    }

    @Override
    public String toString() {
        return modelo;
    }
}
