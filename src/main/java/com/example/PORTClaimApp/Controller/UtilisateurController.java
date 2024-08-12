package com.example.PORTClaimApp.Controller;

import com.example.PORTClaimApp.DTO.UtilisateurDTO;
import com.example.PORTClaimApp.Service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {
    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    PasswordEncoder passwordEncoder;

    //create utilisateur REST API
    @PostMapping
    public ResponseEntity<UtilisateurDTO> createUtilisateur(@RequestBody UtilisateurDTO utilisateurDto){
        UtilisateurDTO savedUtilisateur = utilisateurService.createUtilisateur(utilisateurDto);
        return new ResponseEntity<>(savedUtilisateur, HttpStatus.CREATED);
    }

    //get utilisateur REST API
    @GetMapping("{id}")
    public ResponseEntity<UtilisateurDTO> getUtilisateurById(@PathVariable("id") Long id){
        UtilisateurDTO utilisateurDto = utilisateurService.getUtilisateur(id);
        return ResponseEntity.ok(utilisateurDto);
    }

    //get all utilisateurs REST API
    @GetMapping
    public ResponseEntity<List<UtilisateurDTO>> getAllUtilisateur(){
        List<UtilisateurDTO> utilisateurDtos =utilisateurService.getAllUtilisateurs();
        return ResponseEntity.ok(utilisateurDtos);
    }

    //update utilisateur REST API
    @PutMapping("{id}")
    public ResponseEntity<UtilisateurDTO> updateUtilisateur(@PathVariable("id") Long id,@RequestBody UtilisateurDTO utilisateurDto){
        UtilisateurDTO updatedUtilisateur = utilisateurService.updateUtilisateur(id,utilisateurDto);
        return ResponseEntity.ok(updatedUtilisateur);
    }

    //delete utilisateur REST API

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUtilisateur(@PathVariable("id") Long id){
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.ok("utilisateur supprimé avec succé");
    }
}
