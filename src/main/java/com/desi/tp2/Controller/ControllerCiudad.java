package com.desi.tp2.Controller;

import com.desi.tp2.Model.ModelCiudad;
import com.desi.tp2.Service.ServiceCiudad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ControllerCiudad {

    @Autowired
    private ServiceCiudad ciudadRepository;

    @GetMapping("/ciudades")
    public ModelAndView ciudades(RedirectAttributes ra) throws Exception {
    	
    	ModelAndView mav = new ModelAndView("ciudades");
    	
    	List<ModelCiudad> ciudades = ciudadRepository.findAll();
    	
    	ModelCiudad nuevaCiudad = new ModelCiudad();
    	
    	if(ciudades.isEmpty())
    	{
    		mav.addAllObjects(Map.of("ciudades", ciudades, "nuevaCiudad", nuevaCiudad, "error", "No se encontró ninguna ciudad."));
    	}
    	
    	mav.addAllObjects(Map.of("ciudades", ciudades, "nuevaCiudad", nuevaCiudad));
        
        return mav;
    }

    @PostMapping("/ciudades")
    public ModelAndView crear(ModelCiudad sentCiudad, RedirectAttributes ra) {
    	
        ModelAndView mav = new ModelAndView();
        
        try 
        {
        	ciudadRepository.save(sentCiudad);
            
            ra.addFlashAttribute("exito", "Ciudad creada exitosamente!");
            
            mav.setViewName("redirect:/ciudades");
        } 
        catch (Exception e) 
        {
        	ra.addFlashAttribute("error", "Error al crear la ciudad: " + e.getMessage());
            
            mav.setViewName("redirect:/ciudades");
        }
        
        return mav;
    }


    @PutMapping("/ciudades/{id}")
    public ResponseEntity<ModelCiudad> actualizarCiudad(@PathVariable(value = "id") int idCiudad,
                                                   @RequestBody ModelCiudad sentCiudad) throws Exception {
    	
        Optional<ModelCiudad> ciudad = Optional.ofNullable(ciudadRepository.findById(idCiudad));
        
        if (ciudad.isPresent()) 
        {            
            ModelCiudad updatedCiudad = ciudadRepository.save(sentCiudad);
            
            return ResponseEntity.ok(updatedCiudad);
        } 

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/ciudades/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable(value = "id") int idCiudad, RedirectAttributes ra) throws Exception {
    	
    	Optional<ModelCiudad> ciudad = Optional.ofNullable(ciudadRepository.findById(idCiudad));
        
    	if (ciudad.isPresent()) 
        {
            ciudadRepository.delete(idCiudad);
            
            return ResponseEntity.ok().build();
        } 
        
        return ResponseEntity.notFound().build();
    }
}
