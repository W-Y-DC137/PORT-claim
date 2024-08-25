package com.example.PORTClaimApp.Repository;

import com.example.PORTClaimApp.DTO.UtilisateurDTO;
import com.example.PORTClaimApp.Entity.Utilisateur;
import com.example.PORTClaimApp.Enums.RoleUtilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepo extends JpaRepository<Utilisateur,Long> {
Optional<Utilisateur> findByNomUtilisateur (String nomUtilisateur);

List<Utilisateur> findByRoleIn(List<RoleUtilisateur> roles);

    Optional<Utilisateur> findByEmail(String email);

    Optional<Utilisateur> findByResetToken(String token);
}
