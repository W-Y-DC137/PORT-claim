package com.example.PORTClaimApp.Security;

import com.example.PORTClaimApp.Entity.Utilisateur;
import com.example.PORTClaimApp.Repository.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.PORTClaimApp.Enums.RoleUtilisateur.*;
@Service
public class UtilisateurDetailService implements UserDetailsService {
    @Autowired
    private UtilisateurRepo utilisateurRepo ;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Utilisateur> utilisateur = utilisateurRepo.findByNomUtilisateur(username);
        if(utilisateur.isPresent()){
            var utilisateurObj=utilisateur.get();
            return  User.builder()
                    .username(utilisateurObj.getNomUtilisateur())
                    .password(utilisateurObj.getMotDePasse())
                    .roles(getRoles(utilisateurObj))
                    .build();

        }else {
            throw new UsernameNotFoundException(username);
        }

    }

    private String getRoles(Utilisateur utilisateur){
        if(utilisateur.getRole()==Client){
            return "CLIENT";
        }
        if(utilisateur.getRole()==Agent){
            return "AGENT";
        }
        if(utilisateur.getRole()==Admin){
            return "ADMIN";
        }
        return null;
    }
}
