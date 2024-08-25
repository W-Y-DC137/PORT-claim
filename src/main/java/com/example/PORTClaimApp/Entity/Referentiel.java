package com.example.PORTClaimApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
public class Referentiel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private String valeur;
    private String code;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"code","description","libelle","parent"}, allowSetters = true)
    private Referentiel parent;
}
