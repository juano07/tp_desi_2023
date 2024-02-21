package com.desi.tp2.Service;

import com.desi.tp2.Model.ModelVuelo;
import com.desi.tp2.Repository.RepoVuelo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceVuelo implements ServicioBase<ModelVuelo>{
	
	@Autowired
	private final RepoVuelo repoVuelo;
	
    public ServiceVuelo(RepoVuelo repoVuelo) {
    	
        this.repoVuelo = repoVuelo;        
    }

    @Override
    @Transactional
    public List<ModelVuelo> findAll() throws Exception {
    	
        List<ModelVuelo> vuelos = repoVuelo.findAllOrderedByDate();
        
        return vuelos;
    }

    @Override
    public ModelVuelo findById(int id) throws Exception {
    	
        Optional<ModelVuelo> opt = repoVuelo.findById(id);
        
        return opt.get();
    }

    public List<ModelVuelo> findVuelosByFecha(LocalDateTime fechayhoraVuelo) throws Exception{
    	
    	List<ModelVuelo> vuelos = repoVuelo.findVuelosByFecha(fechayhoraVuelo);
        
        return vuelos;
    }
    
    
    @Override
    @Transactional
    public ModelVuelo save(ModelVuelo entity) {
    	
    	Optional<ModelVuelo> vueloInConflict = repoVuelo.findVueloInConflict(entity.getAvion().getIdAvion(), entity.getFechayhora());
    	
    	if(vueloInConflict.isPresent())
    	{
    		return null;
    	}
    	
        ModelVuelo vuelo = repoVuelo.save(entity);
        
        return vuelo;
    }

    @Override
    @Transactional
    public ModelVuelo update(ModelVuelo sentVuelo) throws Exception {
    	
        Optional<ModelVuelo> opt = repoVuelo.findById(sentVuelo.getIdVuelo());
        
        ModelVuelo updatedVuelo = opt.get();
        
        updatedVuelo = repoVuelo.save(sentVuelo);
        
        return updatedVuelo;
    }

    @Override
    @Transactional
    public boolean delete(int id) throws Exception {
    	
        Optional<ModelVuelo> opt = repoVuelo.findById(id);
        
        if(!opt.isEmpty()){
        	
            ModelVuelo vuelo = opt.get();      
            
            vuelo.setCiudadOrigen(null);
            
            vuelo.setCiudadDestino(null);
            
            repoVuelo.delete(vuelo);            
        }       
        else
        {
        	throw new Exception("No se pudo borrar el vuelo seleccionado.");
        }
        
        return true;
    }
}
