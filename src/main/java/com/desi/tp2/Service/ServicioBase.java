package com.desi.tp2.Service;
import jakarta.transaction.Transactional;
import java.util.List;

public interface ServicioBase<E> {
	
    List<E> findAll() throws Exception;
    
    E findById(int id) throws Exception;   
    
    @Transactional
    E save(E entity) throws Exception;
    
    @Transactional
    E update(E entity) throws Exception;
    
    @Transactional
    boolean delete(int id) throws Exception;
}
