package com.example.PORTClaimApp.DTO;

import com.example.PORTClaimApp.Enums.RoleUtilisateur;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomUtilisateur;
    private String email;
    @Enumerated(EnumType.STRING)
    private RoleUtilisateur role;
    private String motDePasse;

    private String resetToken;

    private LocalDateTime tokenExpiration;
}

