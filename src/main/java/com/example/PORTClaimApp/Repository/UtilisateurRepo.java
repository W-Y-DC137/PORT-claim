package com.example.PORTClaimApp.Repository;

import com.example.PORTClaimApp.Entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepo extends JpaRepository<Utilisateur,Long> {
Optional<Utilisateur> findByNomUtilisateur (String nomUtilisateur);
}
