package com.example.PORTClaimApp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMessage;


    @Column(length = 1000)
    private String contenu;

    private String emetteur;

    private String destinataire;

    @Temporal(TemporalType.TIMESTAMP)
    private Date DateHorloge;


    @ManyToOne
    @JoinColumn(name = "id_ticket")
    private Ticket ticket;

}
