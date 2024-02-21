package com.desi.tp2.Service;
import com.desi.tp2.Model.ModelAvion;
import com.desi.tp2.Repository.RepoAvion;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceAvion implements ServicioBase<ModelAvion>{

    private final RepoAvion repoAvion;

    public ServiceAvion(RepoAvion repoAvion) {
    	
        this.repoAvion = repoAvion;        
    }

    @Override
    @Transactional
    public List<ModelAvion> findAll() throws Exception {
    	
        List<ModelAvion> aviones = repoAvion.findAll();
        
        return aviones;
    }

    @Override
    @Transactional
    public ModelAvion findById(int id) throws Exception {
    	
        Optional<ModelAvion> opt = repoAvion.findById(id);
        
        return opt.get();
    }

    @Override
    @Transactional
    public ModelAvion save(ModelAvion entity) throws Exception {
    	
        Optional<ModelAvion> avionExistente = repoAvion.findById(entity.getIdAvion());
        
        if(avionExistente.isPresent())
        {
        	return null;
        }
        
        ModelAvion avion = repoAvion.save(entity);
        
        return avion;
    }

    @Override
    @Transactional
    public ModelAvion update(ModelAvion sentAvion) throws Exception {
    	
        Optional<ModelAvion> opt = repoAvion.findById(sentAvion.getIdAvion());
        
        ModelAvion updatedAvion = opt.get();
        
        updatedAvion = repoAvion.save(sentAvion);
        
        return updatedAvion;
    }

    @Override
    @Transactional
    public boolean delete(int id) throws Exception{
    	
        Optional<ModelAvion> opt = repoAvion.findById(id);
        
        if(!opt.isEmpty()){
        	
	        ModelAvion avion = opt.get();
	        
	        repoAvion.delete(avion);      
	    }	   
        else
        {
        	throw new Exception("No se pudo borrar el avión seleccionado.");
        }
        
        return true;
    }
}
