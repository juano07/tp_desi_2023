package com.desi.tp2.Controller;
import com.desi.tp2.Model.ModelTicket;
import com.desi.tp2.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@RestController
public class ControllerTicket {
	
    @Autowired
    private ServiceVuelo vueloRepository;
    @Autowired
    private ServiceAvion avionRepository;
    @Autowired
    private ServiceCliente clienteRepository;
    @Autowired
    private ServiceTicket ticketRepository;


    @GetMapping("/tickets")
    public ModelAndView mostrarTickets() throws Exception {
    	
        ModelAndView mav = new ModelAndView("Tickets");
        
        mav.addObject("tickets", ticketRepository.findAll());
        
        return mav;
    }


    @GetMapping("/tickets/{id}")
    public ResponseEntity<ModelTicket> obtenerTicketPorId(@PathVariable(value = "id") int idTicket) throws Exception {
    	
        Optional<ModelTicket> ticket = Optional.ofNullable(ticketRepository.findById(idTicket));
        
        return ticket.map(modelTicket -> ResponseEntity.ok().body(modelTicket)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/tickets/nuevo")
    ModelAndView nuevoForm() throws Exception {

        return new ModelAndView("crearTicket")
                .addObject("ticket", new ModelTicket())
                .addObject("listaDeTickets", ticketRepository.findAll())
                .addObject("listaDeVuelos", vueloRepository.findAll())
                .addObject("listaDeAviones", avionRepository.findAll())
        		.addObject("listaDeClientes", clienteRepository.findAll());
    }

    @PostMapping("/tickets")
    public ModelAndView crear(ModelTicket sentTicket, RedirectAttributes ra) {
    	
        ModelAndView mav = new ModelAndView();
        
        try 
        {
            ticketRepository.save(sentTicket);
            
            ra.addFlashAttribute("exito", "Ticket creado exitosamente!");
            
            mav.setViewName("redirect:/tickets");
            
        } 
        catch (Exception e) 
        {
        	ra.addFlashAttribute("error", "Error al crear el ticket: " + e.getMessage());
            
            mav.setViewName("redirect:/tickets");
            
            //mav.setViewName("error");
            
            //mav.addObject("mensaje", "Error al crear el ticket: " + e.getMessage());
        }
        return mav;
    }

    
    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<Void> eliminarTicket(@PathVariable(value = "id") int id) throws Exception {
    	
        Optional<ModelTicket> ticket = Optional.ofNullable(ticketRepository.findById(id));
        
        if (ticket.isPresent()) 
        {
            ticketRepository.delete(id);
            
            return ResponseEntity.ok().build();
        } 

        return ResponseEntity.notFound().build();
    }
}
