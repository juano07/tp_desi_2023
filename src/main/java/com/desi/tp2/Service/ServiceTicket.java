package com.desi.tp2.Service;

import com.desi.tp2.Model.ModelTicket;
import com.desi.tp2.Repository.RepoTicket;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceTicket implements ServicioBase<ModelTicket>{

    @Autowired
	private RepoTicket repoTicket;

    public ServiceTicket(RepoTicket repoTicket) {
    	
        this.repoTicket = repoTicket;
    }

    @Override
    @Transactional
    public List<ModelTicket> findAll() throws Exception {
    	
        List<ModelTicket> tickets = this.repoTicket.findAll();
        
        return tickets;
    }

    @Override
    @Transactional
    public ModelTicket findById(int id) throws Exception {
    	
        Optional<ModelTicket> opt = this.repoTicket.findById(id);
        
        return opt.get();
    }

    @Override
    @Transactional
    public ModelTicket save(ModelTicket entity) throws Exception {
    	
        ModelTicket ticket = this.repoTicket.save(entity);
        
        return ticket;
    }

    @Override
    @Transactional
    public ModelTicket update(ModelTicket sentTicket) throws Exception {
    	
        Optional<ModelTicket> opt = this.repoTicket.findById(sentTicket.getIdTicket());
        
        ModelTicket updatedTicket = opt.get();
        
        updatedTicket = this.repoTicket.save(sentTicket);
        
        return updatedTicket;
    }

    @Override
    @Transactional
    public boolean delete(int id) throws Exception {
    	
        Optional<ModelTicket> opt = this.repoTicket.findById(id);
        
        if(!opt.isEmpty()){
        	
            ModelTicket ticket = opt.get();
            
            this.repoTicket.save(ticket);
            
        }
        else
        {
        	
            throw new Exception();
            
        }
        
        return true;
    }
}
