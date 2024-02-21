package com.desi.tp2.Controller;

import com.desi.tp2.Model.ModelTasa;
import com.desi.tp2.Service.ServiceTasa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;

@RestController
public class ControllerTasas {

    @Autowired
    private ServiceTasa tasasRepo;

    @GetMapping("/tasas")
    public ModelAndView tasas() throws Exception {
    	
        ModelAndView mav = new ModelAndView("tasas");
        
        mav.addObject("tasas", tasasRepo.findAll());
        
        return mav;
    }

    @PutMapping("/tasas/{id}")
    public ResponseEntity<ModelTasa> actualizarTasa(@PathVariable(value = "id") int id,
    								@RequestBody ModelTasa sentTasa) throws Exception {
    	
		Optional<ModelTasa> tasa = Optional.ofNullable(tasasRepo.findById(id));
	
		if(tasa.isPresent())
		{			
            ModelTasa updatedTasa = tasasRepo.update(sentTasa);
            
            return ResponseEntity.ok(updatedTasa);            
		}           

		return ResponseEntity.notFound().build();
    }
}
