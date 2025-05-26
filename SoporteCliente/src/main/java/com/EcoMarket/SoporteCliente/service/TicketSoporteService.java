package com.EcoMarket.SoporteCliente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate; 

import com.EcoMarket.SoporteCliente.model.TicketSoporte;
import com.EcoMarket.SoporteCliente.repository.TicketSoporteRepository;

import java.time.LocalDateTime;
import java.util.Optional; 

@Service 
public class TicketSoporteService {
    @Autowired
    private TicketSoporteRepository ticketSoporteRepository;

    @SuppressWarnings("unused")
    @Autowired(required = false) 
    private RestTemplate restTemplate; 

    public TicketSoporte crearTicket(TicketSoporte ticketSoporte) {
        if (ticketSoporte.getFechaCreacion() == null) {
            ticketSoporte.setFechaCreacion(LocalDateTime.now());
        }
        if (ticketSoporte.getEstado() == null || ticketSoporte.getEstado().isEmpty()) {
            ticketSoporte.setEstado("Abierto");
        }
        return ticketSoporteRepository.save(ticketSoporte);
    }

    public TicketSoporte cerrarTicket(Long idTicket) {
        Optional<TicketSoporte> ticketOptional = ticketSoporteRepository.findById(idTicket);

        if (ticketOptional.isPresent()) {
            TicketSoporte ticket = ticketOptional.get();
            ticket.setEstado("Cerrado"); 
            return ticketSoporteRepository.save(ticket);
        } else {
            throw new RuntimeException("Ticket de soporte no encontrado con ID: " + idTicket);
        }
    }

    public Optional<TicketSoporte> obtenerTicketPorId(Long idTicket) {
        return ticketSoporteRepository.findById(idTicket);
    }
}
    

