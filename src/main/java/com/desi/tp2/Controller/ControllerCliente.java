package com.desi.tp2.Controller;

import com.desi.tp2.Model.ModelCliente;
import com.desi.tp2.Service.ServiceCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ControllerCliente {

    @Autowired
    private ServiceCliente clienteService;

    @GetMapping("/clientes")
    public ModelAndView clientes(RedirectAttributes ra) throws Exception {
    	
        ModelAndView mav = new ModelAndView("clientes");
        
        List<ModelCliente> clientes = clienteService.findAll();
        
        ModelCliente nuevoCliente =  new ModelCliente();
        
        if (clientes.isEmpty()) 
        {   
            mav.addAllObjects(Map.of("clientes", clientes, "nuevoCliente", nuevoCliente, "error", "No se encontró ningún cliente."));
        } 

        mav.addAllObjects(Map.of("clientes", clientes, "nuevoCliente", nuevoCliente));
        
        return mav;
    }
    
    @PostMapping("/clientes")
    public ModelAndView crear(ModelCliente sentCliente, RedirectAttributes ra) {
    	
        ModelAndView mav = new ModelAndView();
        
        try 
        {        	
            Optional<ModelCliente> clienteCreado = Optional.ofNullable(clienteService.save(sentCliente)); 
            
            if(!clienteCreado.isPresent())
            {
            	throw new Exception("Ya existe un cliente con el dni ingresado");
            }
            
            ra.addFlashAttribute("exito", "Cliente creado exitosamente!");
            
            mav.setViewName("redirect:/clientes");            
        } 
        catch (Exception e) 
        {        	
        	ra.addFlashAttribute("error", "Error al crear el cliente: " + e.getMessage());
            
            mav.setViewName("redirect:/clientes");               
        }
        
        return mav;
    }  
    
    @PutMapping("/clientes/{dni}")
    public ResponseEntity<ModelCliente> actualizarCliente(@PathVariable(value = "dni") int dni,
                                                        @RequestBody ModelCliente sentCliente) throws Exception {    	
    	
        Optional<ModelCliente> cliente = Optional.ofNullable(clienteService.findById(dni));
        
        if (cliente.isPresent()) 
        {   
            ModelCliente updatedCliente = clienteService.update(sentCliente);
            
            return ResponseEntity.ok(updatedCliente);            
        } 
        	
        return ResponseEntity.notFound().build();   
    }

    @DeleteMapping("/clientes/{dni}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable(value = "dni") int dni) throws Exception {
    	
        Optional<ModelCliente> cliente = Optional.ofNullable(clienteService.findById(dni));
        
        if (cliente.isPresent()) 
        {        	
            clienteService.delete(dni);
            
            return ResponseEntity.ok().build();            
        } 

        return ResponseEntity.notFound().build();
    }
}

