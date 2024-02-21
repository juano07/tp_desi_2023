package com.desi.tp2.Service;
import com.desi.tp2.Model.EstadoVuelo;
import com.desi.tp2.Model.ModelAvion;
import com.desi.tp2.Model.ModelTasa;
import com.desi.tp2.Model.ModelVuelo;
import com.desi.tp2.Model.TipoVuelo;
import com.desi.tp2.Model.ModelCiudad;
import com.desi.tp2.Model.ModelCliente;
import com.desi.tp2.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final RepoAvion repoAvion;
    private final RepoCiudad repoCiudad;
    private final RepoCliente repoCliente;
    private final RepoVuelo repoVuelo;
    private final RepoTasas repoTasa;


    public DataLoader(RepoAvion repoAvion, RepoCiudad repoCiudad, RepoCliente repoCliente, RepoVuelo repoVuelo, RepoTasas repoTasa) {
    	
        this.repoAvion = repoAvion;
        
        this.repoCiudad = repoCiudad;
        
        this.repoCliente = repoCliente;
        
        this.repoVuelo = repoVuelo;
        
        this.repoTasa = repoTasa;
    }
    
    @Override
    public void run(String... args) throws ParseException {
      
        if(repoAvion.count() == 0 && repoCiudad.count() == 0 && repoCliente.count() == 0 && repoVuelo.count() == 0 && repoTasa.count() == 0)
        {        	
            repoAvion.save(new ModelAvion("Boeing 737", 50, 4,"Jet Smart"));
            
            repoAvion.save(new ModelAvion("McDonnell Douglas DC-10", 40, 6, "Aerolineas Argentinas"));
            
            repoAvion.save(new ModelAvion("Lockheed L-1011", 40, 6, "Air Europa"));
            
            repoAvion.save(new ModelAvion("Airbus 330", 50, 6, "Aerolíneas Argentinas"));
            
            repoAvion.save(new ModelAvion("Boeing 747", 50 ,6, "Air Canada"));
            
            repoAvion.save(new ModelAvion("Boeing 777", 50, 6, "Air France"));
        
            
            repoCiudad.save(new ModelCiudad("Buenos Aires, Argentina", "1000"));
            
            repoCiudad.save(new ModelCiudad("San Luís, Argentina", "5700"));
            
            repoCiudad.save(new ModelCiudad("Paraná, Argentina", "3100"));
            
            repoCiudad.save(new ModelCiudad("Rosario, Argentina", "2000"));
            
            repoCiudad.save(new ModelCiudad("Los Angeles. USA", "90001"));
            
            repoCiudad.save(new ModelCiudad("Paris, Francia", "70123"));
            
            repoCiudad.save(new ModelCiudad("Tokio, Japon", "1000000"));
       
            
            repoCliente.save(new ModelCliente("Carrillo Ariel", 28717010, "San Martín 2025", "arielcarrillo@gmail.com", LocalDate.parse("1981-03-07"), 15678459));
            
            repoCliente.save(new ModelCliente("Amestoy Paula", 29062477, "Hipolito Yrigoyen 315", "pamestoy@gmail.com", LocalDate.parse("1981-09-17"), 15434756));
            
            repoCliente.save(new ModelCliente("Martinez Celina", 44898733, "Belgrano 126", "celim2002@gmail.com", LocalDate.parse("2002-04-13"), 13674343));
            
            repoCliente.save(new ModelCliente("Hidalgo Juan Pablo", 48763456, "Tucumán 1120", "jphidalgo@gmail.com", LocalDate.parse("2006-12-06"), 15677789));
        
            
        	repoVuelo.save(new ModelVuelo((repoCiudad.findById(1)).get(), (repoCiudad.findById(2)).get(), TipoVuelo.NACIONAL, 120000.00, LocalDateTime.parse("2024-01-21T15:00"), (repoAvion.findById(1)).get(), EstadoVuelo.REPROGRAMADO));
        	
        	repoVuelo.save(new ModelVuelo((repoCiudad.findById(1)).get(), (repoCiudad.findById(4)).get(), TipoVuelo.NACIONAL, 120000.00, LocalDateTime.parse("2024-01-21T17:00"), (repoAvion.findById(2)).get(), EstadoVuelo.NORMAL));
        	
        	repoVuelo.save(new ModelVuelo((repoCiudad.findById(1)).get(), (repoCiudad.findById(5)).get(), TipoVuelo.INTERNACIONAL, 890000.00, LocalDateTime.parse("2024-01-22T21:00"), (repoAvion.findById(3)).get(), EstadoVuelo.NORMAL));
        	
        	repoVuelo.save(new ModelVuelo((repoCiudad.findById(1)).get(), (repoCiudad.findById(6)).get(), TipoVuelo.INTERNACIONAL, 1250000.00, LocalDateTime.parse("2024-01-23T23:00"), (repoAvion.findById(4)).get(), EstadoVuelo.NORMAL));        	
        
        	
            repoTasa.save(new ModelTasa("Tasa General", 1250.00, 21.00, 3, 2500, 1000));
        }      
    }
}
