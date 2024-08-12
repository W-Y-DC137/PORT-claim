package com.example.PORTClaimApp.Service;

import com.example.PORTClaimApp.DTO.UtilisateurDTO;
import com.example.PORTClaimApp.Entity.Ticket;
import com.example.PORTClaimApp.Entity.Utilisateur;

import java.util.List;

public interface UtilisateurService {
   // Utilisateur createById(Long id);
    UtilisateurDTO createUtilisateur(UtilisateurDTO utilisateurDTO);
    UtilisateurDTO getUtilisateur(Long id);
    List<UtilisateurDTO> getAllUtilisateurs();
    UtilisateurDTO updateUtilisateur(Long id ,UtilisateurDTO utilisateurDTO);
    void deleteUtilisateur(Long id);
}
