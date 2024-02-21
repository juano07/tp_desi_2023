package com.desi.tp2.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class ModelTicket {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTicket;

    @ManyToOne(targetEntity = ModelVuelo.class)
    private  ModelVuelo vuelo;

    @ManyToOne(targetEntity = ModelCliente.class)
    private ModelCliente cliente;
    
    private double precio;

    private LocalDateTime fechayhoraVuelo;

    private LocalDateTime fechayhoraCompra;

    public ModelTicket() {
    }

    public ModelTicket(ModelVuelo vuelo, ModelCliente cliente, ModelAvion avion, ModelAvion asiento, int asientoFila,
			char asientoLetra, double precio, LocalDateTime fechayhoraVuelo, LocalDateTime fechayhoraCompra) {
		this.vuelo = vuelo;
		this.cliente = cliente;
		this.precio = precio;
		this.fechayhoraVuelo = fechayhoraVuelo;
		this.fechayhoraCompra = fechayhoraCompra;
	}

	public int getIdTicket() {
        return idTicket;
    }

    public ModelVuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(ModelVuelo vuelo) {
        this.vuelo = vuelo;
    }

    public ModelCliente getCliente() {
        return cliente;
    }

    public void setCliente(ModelCliente cliente) {
        this.cliente = cliente;
    }
    
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDateTime getFechaVuelo() {
        return fechayhoraVuelo;
    }

    public void setFechaVuelo(LocalDateTime fechayhoraVuelo) {
        this.fechayhoraVuelo = fechayhoraVuelo;
    }

    public LocalDateTime getFechaTicket() {
        return fechayhoraCompra;
    }

    public void setFechaTicket(LocalDateTime fechayhoraCompra) {
        this.fechayhoraCompra = fechayhoraCompra;
    }

	@Override
	public int hashCode() {
		return Objects.hash(cliente, idTicket, precio, vuelo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelTicket other = (ModelTicket) obj;
		return Objects.equals(idTicket, other.idTicket);
	}
}
