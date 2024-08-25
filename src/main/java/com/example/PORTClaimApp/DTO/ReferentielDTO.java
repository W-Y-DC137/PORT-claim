package com.example.PORTClaimApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReferentielDTO {
    private Long id;

    private String libelle;
    private String valeur;
    private String code;
    private ReferentielDTO parent;
}
