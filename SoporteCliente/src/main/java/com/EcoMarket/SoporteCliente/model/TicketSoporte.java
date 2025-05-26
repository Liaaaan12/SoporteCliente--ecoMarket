package com.EcoMarket.SoporteCliente.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketSoporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(nullable = false)
    private Long idUsuario; 

    @Column(nullable = false)
    private String asunto; 

    @Column(nullable = false, length = 300)
    private String descripcion; 

    @Column(nullable = false)
    private String estado; 

    @Column(nullable = false)
    private LocalDateTime fechaCreacion; 

}
