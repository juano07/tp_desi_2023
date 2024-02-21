package com.desi.tp2.Service;

import com.desi.tp2.Model.ModelCiudad;
import com.desi.tp2.Repository.RepoCiudad;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceCiudad implements ServicioBase<ModelCiudad>{
	
    private final RepoCiudad repoCiudad;

    public ServiceCiudad(RepoCiudad repoCiudad) {
    	
        this.repoCiudad = repoCiudad;
    }

    @Override
    @Transactional
    public List<ModelCiudad> findAll() throws Exception {
    	
        List<ModelCiudad> ciudades = this.repoCiudad.findAll();
        
        return ciudades;
    }

    @Override
    @Transactional
    public ModelCiudad findById(int id) throws Exception {
    	
        Optional<ModelCiudad> opt = this.repoCiudad.findById(id);
        
        return opt.get();
    }

    @Override
    @Transactional
    public ModelCiudad save(ModelCiudad entity) throws Exception {
    	
        ModelCiudad ciudad = this.repoCiudad.save(entity);
        
        return ciudad;
    }

    @Override
    @Transactional
    public ModelCiudad update(ModelCiudad sentCiudad) throws Exception {
    	
        Optional<ModelCiudad> opt = this.repoCiudad.findById(sentCiudad.getIdCiudad());
        
        ModelCiudad updatedCiudad = opt.get();
        
        updatedCiudad = this.repoCiudad.save(sentCiudad);
        
        return updatedCiudad;
    }

    @Override
    @Transactional
    public boolean delete(int id) throws Exception {
    	
        Optional<ModelCiudad> opt = repoCiudad.findById(id);
        
        if(!opt.isEmpty())
        {
        	ModelCiudad ciudad = opt.get();
        	
            repoCiudad.delete(ciudad);
        }
        else
        {
            throw new Exception("No se pudo borrar la ciudad seleccionado.");
        }
        
        return true;
    }
}