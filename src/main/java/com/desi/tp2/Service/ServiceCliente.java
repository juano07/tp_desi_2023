package com.desi.tp2.Service;

import com.desi.tp2.Model.ModelCliente;
import com.desi.tp2.Repository.RepoCliente;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceCliente implements ServicioBase<ModelCliente>{


    private final RepoCliente repoCliente;

    public ServiceCliente(RepoCliente repoCliente) {
    	
        this.repoCliente = repoCliente;        
    }

    @Override
    public List<ModelCliente> findAll() throws Exception {

        List<ModelCliente> clientes = repoCliente.findAll();
        
        return clientes;
    }

    @Override
    @Transactional
    public ModelCliente findById(int dni) throws Exception {
    	
        Optional<ModelCliente> opt = repoCliente.findById(dni);
        
        return opt.get();        
    }
    
    @Override
    @Transactional
    public ModelCliente save(ModelCliente entity) throws Exception {
    	
    	Optional<ModelCliente> clienteExistente = repoCliente.findById(entity.getDni());
    	
    	if(clienteExistente.isPresent())
    	{
    		return null;
    	}
    	
        ModelCliente cliente = repoCliente.save(entity);
        
        return cliente;        
    }

    @Override
    @Transactional
    public ModelCliente update(ModelCliente sentCliente) throws Exception {
    	
        Optional<ModelCliente> opt = repoCliente.findById(sentCliente.getDni());
        
        ModelCliente updatedCliente = opt.get();        
        
        updatedCliente = repoCliente.save(sentCliente);
        
        return updatedCliente;        
    }

    @Override
    @Transactional
    public boolean delete(int dni) throws Exception{
    	
        Optional<ModelCliente> opt = repoCliente.findById(dni);
        
        if(!opt.isEmpty()){
        	
	        ModelCliente cliente = opt.get();
	        
	        repoCliente.delete(cliente);        
	    }
        else
        {	    	
	        throw new Exception("No se pudo borrar el cliente seleccionado.");
	    }
        
        return true;
    }
}
