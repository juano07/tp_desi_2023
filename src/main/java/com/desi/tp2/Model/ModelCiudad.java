package com.desi.tp2.Model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class ModelCiudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCiudad;

    private String nombreCiudad;

    private String codigoPostal;

    public ModelCiudad() {
    	
    }
    
    public ModelCiudad(String nombreCiudad, String codigoPostal) {
    	
        this.nombreCiudad = nombreCiudad;
        
        this.codigoPostal = codigoPostal;
    }

    public int getIdCiudad() {
    	
        return idCiudad;
    }

    public String getNombreCiudad() {
    	
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
    	
        this.nombreCiudad = nombreCiudad;
    }

    public String getCodigoPostal() {
    	
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
    	
        this.codigoPostal = codigoPostal;
    }

    @Override
    public String toString() {
    	
        return nombreCiudad;
    }

    @Override
    public boolean equals(Object o) {
    	
        if (this == o) return true;
        
        if (!(o instanceof ModelCiudad anotherObject)) return false;
        
        return idCiudad == anotherObject.idCiudad;
    }

    @Override
    public int hashCode() {
    	
        return Objects.hash(idCiudad, nombreCiudad, codigoPostal);
    }
}
