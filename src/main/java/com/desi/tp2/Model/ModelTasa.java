package com.desi.tp2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.Objects;

@Entity
public class ModelTasa {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTasa;

    private String nombreCP;

    @Min(0)
    private double precioCP;

    @Min(0)@Max(100)
    private double iva;

    @Min(0)
    @Column(nullable = false )
    private double tasaInternacional;

    @Min(0)
    @Column( nullable = false )
    private double tasaNacional;

    @Min(0)
    private int cotizacionUSD;

    public ModelTasa() {
    }

    public ModelTasa(String _nombreCP, double _precioCP, double _iva, double _tasaInternacional, double _tasaNacional, int _cotizacionUSD) {
        this.nombreCP = _nombreCP;
        this.precioCP = _precioCP;
        this.iva = _iva;
        this.tasaInternacional = _tasaInternacional;
        this.tasaNacional = _tasaNacional;
        this.cotizacionUSD = _cotizacionUSD;
    }

    public int getIdTasa() {
        return idTasa;
    }

    public String getNombreCP() {
        return nombreCP;
    }

    public void setNombreCP(String nombreCP) {
        this.nombreCP = nombreCP;
    }

    public double getPrecioCP() {
        return precioCP;
    }

    public void setPrecioCP(double precioCP) {
        this.precioCP = precioCP;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
    	
        this.iva = iva;
    }

    public double getTasaInternacional() {
        return tasaInternacional;
    }

    public void setTasaInternacional(double _tasaInternacional) {
    	
        this.tasaInternacional = _tasaInternacional;
    }

    public double getTasaNacional() {
    	
        return tasaNacional;
    }

    public void setTasaNacional(double _tasaNacional) {
    	
        this.tasaNacional = _tasaNacional;
    }

    public int getCotizacionUSD() {
    	
        return cotizacionUSD;
    }

    public void setCotizacionUSD(int _cotizacionUSD) {
    	
        this.cotizacionUSD = _cotizacionUSD;
    }

    @Override
    public boolean equals(Object o) {
    	
        if (this == o) return true;
        
        if (!(o instanceof ModelTasa modelCP)) 
    	{
        	return false;
    	}
        
        return idTasa == modelCP.idTasa && Double.compare(modelCP.precioCP, precioCP) == 0 && Double.compare(modelCP.iva, iva) == 0 && Double.compare(modelCP.tasaInternacional, tasaInternacional) == 0 && Double.compare(modelCP.tasaNacional, tasaNacional) == 0 && cotizacionUSD == modelCP.cotizacionUSD && Objects.equals(nombreCP, modelCP.nombreCP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTasa, nombreCP, precioCP, iva, tasaInternacional, tasaNacional, cotizacionUSD);
    }
}
