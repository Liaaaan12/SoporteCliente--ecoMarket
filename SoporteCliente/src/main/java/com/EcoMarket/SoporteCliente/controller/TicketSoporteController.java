package com.EcoMarket.SoporteCliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.EcoMarket.SoporteCliente.model.TicketSoporte;
import com.EcoMarket.SoporteCliente.service.TicketSoporteService;

@RestController
@RequestMapping("/api/tickets-soporte")
public class TicketSoporteController {
    @Autowired
    private TicketSoporteService ticketSoporteService;

    public ResponseEntity<TicketSoporte> crearTicket(@RequestBody TicketSoporte ticketSoporte) {
        try {
            TicketSoporte nuevoTicket = ticketSoporteService.crearTicket(ticketSoporte);
            return new ResponseEntity<>(nuevoTicket, HttpStatus.CREATED); 
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    @PutMapping("/{id}/cerrar")
    public ResponseEntity<TicketSoporte> cerrarTicket(@PathVariable Long id) {
        try {
            TicketSoporte ticketCerrado = ticketSoporteService.cerrarTicket(id);
            return new ResponseEntity<>(ticketCerrado, HttpStatus.OK); 
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<TicketSoporte> obtenerTicketPorId(@PathVariable Long id) {
        return ticketSoporteService.obtenerTicketPorId(id)
                .map(ticket -> new ResponseEntity<>(ticket, HttpStatus.OK)) 
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); 
    }
}

 