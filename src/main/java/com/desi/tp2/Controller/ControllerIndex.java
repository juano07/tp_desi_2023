package com.desi.tp2.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.desi.tp2.Model.ModelVuelo;
import com.desi.tp2.Service.ServiceVuelo;

@Controller
public class ControllerIndex {

    @Autowired
    private ServiceVuelo vueloService;
 
    @GetMapping("/indexPasajeros")
    ModelAndView indexPasajeros() throws Exception {

    	ModelAndView mav = new ModelAndView("indexPasajeros");
    	
    	List<ModelVuelo> vuelos = vueloService.findAll();    	
    	
    	mav.addAllObjects(Map.of("vuelos", vuelos));
        
        return mav;
    }
    
    @GetMapping("/indexAgencias")
    ModelAndView indexAgencias() throws Exception {

    	ModelAndView mav = new ModelAndView("indexAgencias");
    	
    	List<ModelVuelo> vuelos = vueloService.findAll();    	
    	
    	mav.addAllObjects(Map.of("vuelos", vuelos));
        
        return mav;
    }
    
    @GetMapping("/indexOperarios")
    ModelAndView indexOperarios() throws Exception {

    	ModelAndView mav = new ModelAndView("indexOperarios");
    	
    	List<ModelVuelo> vuelos = vueloService.findAll();    	
    	
    	mav.addAllObjects(Map.of("vuelos", vuelos));
        
        return mav;
    }
}