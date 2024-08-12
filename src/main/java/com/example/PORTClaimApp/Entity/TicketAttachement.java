package com.example.PORTClaimApp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class TicketAttachement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTicketAttacnement;

    @Column(nullable = false)
    private String fileName ;

    @Column(nullable = false)
    private String fileType;

    @Lob
    private byte[] fileData;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;
}

