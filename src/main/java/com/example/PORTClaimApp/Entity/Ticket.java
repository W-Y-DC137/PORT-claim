package com.example.PORTClaimApp.Entity;

import com.example.PORTClaimApp.Enums.NiveauUrgence;
import com.example.PORTClaimApp.Enums.StatutTicket;
import com.example.PORTClaimApp.Enums.TypeTicket;
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
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTicket;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeTicket type;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Referentiel theme;

    @ManyToOne
    @JoinColumn(name = "sousTheme_id")
    private Referentiel sousTheme;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NiveauUrgence niveauUrgence;

    @Column(nullable = false)
    private String objet;

    @Column(nullable = false,length = 1000)
    private String description ;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatutTicket statutTicket;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOuverture;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateResolution;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFermeture;


    @ManyToOne
    @JoinColumn(name = "idClient",nullable = false)
    private Utilisateur client;
    @ManyToOne
    @JoinColumn(name = "idAgent",nullable = true)
    private Utilisateur agent;
    @ManyToOne
    @JoinColumn(name = "idAdmin",nullable = true)
    private Utilisateur admin;
}

