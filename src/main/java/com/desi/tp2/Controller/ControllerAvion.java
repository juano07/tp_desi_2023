package com.desi.tp2.Controller;

import com.desi.tp2.Model.ModelAvion;
import com.desi.tp2.Service.ServiceAvion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ControllerAvion {

    @Autowired
    private ServiceAvion avionService;

    @GetMapping("/aviones")
    public ModelAndView aviones() throws Exception {
    	
        ModelAndView mav = new ModelAndView("aviones");
        
        List<ModelAvion> aviones = avionService.findAll();
        
        ModelAvion nuevoAvion = new ModelAvion();
        
        if(aviones.isEmpty())
        {
        	mav.addAllObjects(Map.of("aviones", aviones, "nuevoAvion", nuevoAvion, "error", "No se encontró ningún avión."));
        }
        
        mav.addAllObjects(Map.of("aviones", aviones, "nuevoAvion", nuevoAvion));      
               
        return mav;
    }

    @PostMapping("/aviones")
    public ModelAndView crearAvion(ModelAvion sentAvion, RedirectAttributes ra) {
    	
        ModelAndView mav = new ModelAndView();
        
        try 
        {
            avionService.save(sentAvion);
            
            ra.addFlashAttribute("exito", "Avión creado exitosamente!");
            
            mav.setViewName("redirect:/aviones");            
        } 
        catch (Exception e) 
        {
    		ra.addFlashAttribute("error", "Error al crear el avión: " + e.getMessage());
            
            mav.setViewName("redirect:/aviones");
        }
        
        return mav;
    }
    
    @PutMapping("/aviones/{id}")
    public ResponseEntity<ModelAvion> actualizarAvion(@PathVariable(value = "id") int idAvion,
                                                        @RequestBody ModelAvion sentAvion) throws Exception {    
       
        Optional<ModelAvion> avion = Optional.ofNullable(avionService.findById(idAvion));
        
        if (avion.isPresent()) 
        {   
            ModelAvion updatedAvion = avionService.update(sentAvion);
            
            return ResponseEntity.ok(updatedAvion);            
        } 
        	
        return ResponseEntity.notFound().build();             
    }
    
    @DeleteMapping("/aviones/{id}")
    public ResponseEntity<Void> eliminarAvion(@PathVariable(value = "id") int idAvion) throws Exception {
    	
        Optional<ModelAvion> avion = Optional.ofNullable(avionService.findById(idAvion));
        
        if (avion.isPresent()) 
        {
            avionService.delete(idAvion);
            
            return ResponseEntity.ok().build();
        } 
        
        return ResponseEntity.notFound().build();
    }
}

