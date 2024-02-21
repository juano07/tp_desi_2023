package com.desi.tp2.Service;

import com.desi.tp2.Model.ModelTasa;
import com.desi.tp2.Repository.RepoTasas;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceTasa implements ServicioBase<ModelTasa>{

    private final RepoTasas repoTasas;

    public ServiceTasa(RepoTasas _repoTasas) {
    	
        this.repoTasas = _repoTasas;
    }

    @Override
    @Transactional
    public List<ModelTasa> findAll() throws Exception {
    	
        return this.repoTasas.findAll();
    }

    @Override
    @Transactional
    public ModelTasa findById(int _id) throws Exception {
    	
        Optional<ModelTasa> opt = this.repoTasas.findById(_id);
        
        ModelTasa tasaEncontrada = opt.get();
        
        return tasaEncontrada;
    }

    @Override
    @Transactional
    public ModelTasa save(ModelTasa _tasa) throws Exception {
    	
        ModelTasa tasa = this.repoTasas.save(_tasa);
        
        return tasa;
    }

    @Override
    @Transactional
    public ModelTasa update(ModelTasa sentTasa) throws Exception {
    	
        Optional<ModelTasa> opt = this.repoTasas.findById(sentTasa.getIdTasa());
        
        ModelTasa updatedTasa = opt.get();
        
        updatedTasa = this.repoTasas.save(sentTasa);
        
        return updatedTasa;
    }

    @Override
    @Transactional
    public boolean delete(int _id) throws Exception {
    	
        Optional<ModelTasa> opt = this.repoTasas.findById(_id);
        
        boolean deleted = false;
        
        if(!opt.isEmpty()){
        	
            ModelTasa tasaBorrada = opt.get();
            
            this.repoTasas.delete(tasaBorrada);
            
            deleted = true;
        }
        
        return deleted;
    }
}
