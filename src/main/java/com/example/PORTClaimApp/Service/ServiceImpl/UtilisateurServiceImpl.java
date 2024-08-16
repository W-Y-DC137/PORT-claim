package com.example.PORTClaimApp.Service.ServiceImpl;

import com.example.PORTClaimApp.DTO.UtilisateurDTO;
import com.example.PORTClaimApp.Entity.Utilisateur;
import com.example.PORTClaimApp.Exception.RessourceNotFoundException;
import com.example.PORTClaimApp.Mapper.UtilisateurMapper;
import com.example.PORTClaimApp.Repository.UtilisateurRepo;
import com.example.PORTClaimApp.Service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {
    UtilisateurRepo utilisateurRepo;

    private PasswordEncoder passwordEncoder;

    @Override
    public UtilisateurDTO createUtilisateur(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = UtilisateurMapper.mapToUtilisateur(utilisateurDTO);
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
        Utilisateur savedUtilisateur = utilisateurRepo.save(utilisateur);
        return UtilisateurMapper.mapToUtilisateurDto(savedUtilisateur);
    }

    @Override
    public UtilisateurDTO getUtilisateur(Long id) {
        Utilisateur utilisateur = utilisateurRepo.findById(id)
                .orElseThrow(()->
                        new RessourceNotFoundException("Nous n'avons trouvé aucun utilisateur correspondant à l'ID fourni : " + id + ". Veuillez vérifier l'ID et réessayer")
                );
        return UtilisateurMapper.mapToUtilisateurDto(utilisateur);
    }

    @Override
    public List<UtilisateurDTO> getAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurRepo.findAll();
        return utilisateurs.stream().map((utilisateur)->
                        UtilisateurMapper.mapToUtilisateurDto(utilisateur)).
                collect(Collectors.toList());
    }

    @Override
    public UtilisateurDTO updateUtilisateur(Long id, UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = utilisateurRepo.findById(id)
                .orElseThrow(()->
                        new RessourceNotFoundException("Nous n'avons trouvé aucun utilisateur correspondant à l'ID fourni : " + id + ". Veuillez vérifier l'ID et réessayer")
                );
        utilisateur.setNomUtilisateur(utilisateurDTO.getNomUtilisateur());
        utilisateur.setEmail(utilisateurDTO.getEmail());
        utilisateur.setRole(utilisateurDTO.getRole());
        utilisateur.setMotDePasse(utilisateurDTO.getMotDePasse());
        Utilisateur savedUtilisateur = utilisateurRepo.save(utilisateur);
        return UtilisateurMapper.mapToUtilisateurDto(savedUtilisateur);
    }

    @Override
    public void deleteUtilisateur(Long id) {
        Utilisateur utilisateur=utilisateurRepo.findById(id)
                .orElseThrow(()->
                        new RessourceNotFoundException("Nous n'avons trouvé aucun utilisateur correspondant à l'ID fourni : " + id + ". Veuillez vérifier l'ID et réessayer")
                );
        utilisateurRepo.deleteById(id);
    }
}
