package com.desi.tp2.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ModelVuelo implements Comparable<ModelVuelo>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVuelo;

    @ManyToOne
    @JoinColumn(name = "idOrigen")
    private ModelCiudad ciudadOrigen;

    @ManyToOne
    @JoinColumn(name = "idDestino")
    private ModelCiudad ciudadDestino;
    
    @Enumerated(EnumType.STRING)
    private TipoVuelo tipoVuelo;

    private double precioVuelo;

    @DateTimeFormat(pattern = "yyyy-mm-dd'T'hh:mm")
    private LocalDateTime fechayhora;

    @ManyToOne
    @JoinColumn(name = "idAvion")
    private ModelAvion avion;

    @Enumerated(EnumType.STRING)
    private EstadoVuelo estadoVuelo;

    private int asientosDisponibles;
    
    public ModelVuelo() {
    	
    }

    public ModelVuelo(ModelCiudad ciudadOrigen, ModelCiudad ciudadDestino, TipoVuelo tipo, double precioVuelo, LocalDateTime fechayhora, ModelAvion avion, EstadoVuelo estado) {
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.tipoVuelo = tipo;
        this.fechayhora = fechayhora;
        this.avion = avion;
        this.estadoVuelo = estado;
        this.precioVuelo = precioVuelo;
        this.asientosDisponibles = this.avion.getFilas() * this.avion.getAsientosXFila();
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public ModelCiudad getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(ModelCiudad ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public ModelCiudad getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(ModelCiudad ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public TipoVuelo getTipoVuelo() {
        return tipoVuelo;
    }

    public void setTipoVuelo(TipoVuelo tipo) {
        this.tipoVuelo = tipo;
    }

    public double getPrecioVuelo() {
        return precioVuelo;
    }

    public void setPrecioVuelo(double precioVuelo) {
        this.precioVuelo = precioVuelo;
    }

    public LocalDateTime getFechayhora() {
        return fechayhora;
    }
    
    public void setFechayhora(LocalDateTime fechayhora) {
        this.fechayhora = fechayhora;
    }

    public ModelAvion getAvion() {
        return avion;
    }

    public void setAvion(ModelAvion avion) {
        this.avion = avion;
    }

    public EstadoVuelo getEstadoVuelo() {
        return estadoVuelo;
    }

    public void setEstadoVuelo(EstadoVuelo estadoVuelo) {
        this.estadoVuelo = estadoVuelo;
    }
	
	public int getAsientosDisponibles()
	{
		return asientosDisponibles;
	}
	
	public void calcAsientosDisponibles()
	{
		this.asientosDisponibles = this.avion.getFilas() * this.avion.getAsientosXFila();
	}

	public boolean reservarAsiento(int cantidadRequerida) {
		
		if(this.asientosDisponibles - cantidadRequerida >= 0)
		{
			this.asientosDisponibles -= cantidadRequerida;
			
			return true;
		}
		
		return false;
	}

	@Override
    public boolean equals(Object o) {
		
        if (this == o) return true;
        
        if (!(o instanceof ModelVuelo anotherObject)) return false;
        
        return getIdVuelo() == anotherObject.getIdVuelo();
    }

    @Override
    public int hashCode() {
    	
        return Objects.hash(getIdVuelo(),
                getCiudadOrigen(),
                getCiudadDestino(),
                getTipoVuelo(),
                getPrecioVuelo(),
                getFechayhora(),
                getAvion(),
                getEstadoVuelo());
    }

    
    
    @Override
	public String toString() {
    	
		return "ModelVuelo [idVuelo= " + idVuelo + ", ciudadOrigen= " + ciudadOrigen + ", ciudadDestino= " + ciudadDestino
				+ ", tipo de vuelo= " + tipoVuelo + ", precio= " + precioVuelo + ", fecha y hora= " + fechayhora + ", avion= "
				+ avion + ", estado del vuelo= " + estadoVuelo + ", asientosDisponibles= "
				+ asientosDisponibles + "]";
	}

	@Override
    public int compareTo(ModelVuelo otroVuelo) {
        return this.fechayhora.compareTo(otroVuelo.getFechayhora());
    }
}
