package com.example.PORTClaimApp.Mapper;

import com.example.PORTClaimApp.DTO.UtilisateurDTO;
import com.example.PORTClaimApp.Entity.Utilisateur;
import jdk.jshell.execution.Util;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper {
    public static UtilisateurDTO mapToUtilisateurDto(Utilisateur utilisateur){
        UtilisateurDTO utilisateurDto = new UtilisateurDTO(
                utilisateur.getId(),
                utilisateur.getNomUtilisateur(),
                utilisateur.getEmail(),
                utilisateur.getRole(),
                utilisateur.getMotDePasse(),
                utilisateur.getResetPasswordToken()

        );
        return utilisateurDto;
    }

    public static Utilisateur mapToUtilisateur(UtilisateurDTO utilisateurDto){
        Utilisateur utilisateur = new Utilisateur(
                utilisateurDto.getId(),
                utilisateurDto.getNomUtilisateur(),
                utilisateurDto.getEmail(),
                utilisateurDto.getRole(),
                utilisateurDto.getMotDePasse(),
                utilisateurDto.getResetPasswordToken()
        );
        return utilisateur;
    }
}
