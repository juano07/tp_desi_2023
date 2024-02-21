package com.desi.tp2.Controller;

import com.desi.tp2.Model.EstadoVuelo;
import com.desi.tp2.Model.ModelAvion;
import com.desi.tp2.Model.ModelCiudad;
import com.desi.tp2.Model.ModelCliente;
import com.desi.tp2.Model.ModelVuelo;
import com.desi.tp2.Model.TipoVuelo;
import com.desi.tp2.Service.ServiceAvion;
import com.desi.tp2.Service.ServiceCiudad;
import com.desi.tp2.Service.ServiceCliente;
import com.desi.tp2.Service.ServiceVuelo;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ControllerVuelo {

    @Autowired
    private ServiceCiudad ciudadService;
    @Autowired
    private ServiceVuelo vueloService;
    @Autowired
    private ServiceAvion avionService;
    @Autowired
    private ServiceCliente clienteService;
    
    @GetMapping("/vuelos")
    public ModelAndView vuelos() throws Exception {
    	
    	ModelAndView mav = new ModelAndView("vuelos");
    	
    	List<ModelVuelo> vuelos = vueloService.findAll();
    	
    	List<Object> objectList = new ArrayList<>();
    	
    	List<String> tiposVuelos = TipoVuelo.getListaTiposVuelo();
    	
    	List<String> estadosVuelos = EstadoVuelo.getListaEstados(); 
    	
    	List<ModelCiudad> ciudades = ciudadService.findAll();
    	
    	List<ModelAvion> aviones = avionService.findAll();
    	
    	List<ModelCliente> clientes = clienteService.findAll();
    	
    	objectList.add(tiposVuelos);
    	objectList.add(estadosVuelos);
    	objectList.add(ciudades);
    	objectList.add(aviones);
    	objectList.add(clientes);
    	
    	if(vuelos.isEmpty())
    	{
    		mav.addAllObjects(Map.of("vuelos", vuelos, "objectList", objectList, "error", "No se encontró ningún vuelo."));
    	}
        
    	mav.addAllObjects(Map.of("vuelos", vuelos, "objectList", objectList));
        
        return mav;
    }

    @SuppressWarnings("rawtypes")
	@PostMapping("/vuelos")
    public ResponseEntity crearVuelo(@RequestBody ObjectNode jsonObject, RedirectAttributes ra) {
       
        try 
        {          	
        	ModelVuelo nuevoVuelo = new ModelVuelo(
        			ciudadService.findById(Integer.parseInt(jsonObject.get("ciudadOrigen").asText())),
        			ciudadService.findById(Integer.parseInt(jsonObject.get("ciudadDestino").asText())),
        			TipoVuelo.valueOf(jsonObject.get("tipoVuelo").asText()),
        			Double.parseDouble(jsonObject.get("precioVuelo").asText()),
        			LocalDateTime.parse(jsonObject.get("fechayhora").asText()),
        			avionService.findById(Integer.parseInt(jsonObject.get("avion").asText())),
        			EstadoVuelo.valueOf(jsonObject.get("estadoVuelo").asText())
        			);
        	
            ModelVuelo vueloCreado = vueloService.save(nuevoVuelo);
            
            if(vueloCreado != null)
            {
            	return ResponseEntity.ok().build();
            }
            else
            {
            	return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("El avión seleccionado ya tiene un vuelo programado para la fecha seleccionada.");
            }
            
        } 
        catch (Exception e) 
        {
        	return ResponseEntity.internalServerError().build();
        }
    }    

    @PutMapping("/vuelos/{id}")
    public ResponseEntity<ModelVuelo> actualizarVuelo(@PathVariable(value = "id") int idVuelo,
                                                        @RequestBody ObjectNode jsonObject) throws Exception {
    	
        Optional<ModelVuelo> vuelo = Optional.ofNullable(vueloService.findById(idVuelo));
        
        if (vuelo.isPresent()) 
        {
        	ModelVuelo updatedVuelo = vuelo.get();
        	
        	updatedVuelo.setCiudadOrigen(ciudadService.findById(Integer.parseInt(jsonObject.get("ciudadOrigen").asText())));
        	
        	updatedVuelo.setCiudadDestino(ciudadService.findById(Integer.parseInt(jsonObject.get("ciudadDestino").asText())));
        	
        	updatedVuelo.setTipoVuelo(TipoVuelo.valueOf(jsonObject.get("tipoVuelo").asText()));
        	
        	updatedVuelo.setPrecioVuelo(Double.parseDouble(jsonObject.get("precioVuelo").asText()));
        	
        	updatedVuelo.setFechayhora(LocalDateTime.parse(jsonObject.get("fechayhora").asText()));
        	
        	updatedVuelo.setAvion(avionService.findById(Integer.parseInt(jsonObject.get("avion").asText())));
        	
        	updatedVuelo.setEstadoVuelo(EstadoVuelo.valueOf(jsonObject.get("estadoVuelo").asText()));

        	updatedVuelo.calcAsientosDisponibles();
        	
            return ResponseEntity.ok(vueloService.save(updatedVuelo));
        } 

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/vuelos/{id}")
    public ResponseEntity<Void> eliminarVuelo(@PathVariable(value = "id") int idVuelo) throws Exception {
    	
    	Optional<ModelVuelo> vuelo = Optional.ofNullable(vueloService.findById(idVuelo));
    	
        if(vuelo.isPresent())
        {
        	vueloService.delete(idVuelo);
        	
        	return ResponseEntity.ok().build();
        }
        
        return ResponseEntity.notFound().build();
    }    
}
