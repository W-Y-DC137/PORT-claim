package com.example.PORTClaimApp.Entity;

import com.example.PORTClaimApp.Enums.RoleUtilisateur;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomUtilisateur;
    private String email;
    @Enumerated(EnumType.STRING)
    private RoleUtilisateur role;
    private String motDePasse;
}
