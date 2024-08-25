package com.example.PORTClaimApp.Controller;

import com.example.PORTClaimApp.DTO.ChangePasswordDTO;
import com.example.PORTClaimApp.DTO.UtilisateurDTO;
import com.example.PORTClaimApp.Enums.RoleUtilisateur;
import com.example.PORTClaimApp.Mapper.UtilisateurMapper;
import com.example.PORTClaimApp.Repository.UtilisateurRepo;
import com.example.PORTClaimApp.Service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin("*")

public class UtilisateurController {
    @Autowired
    UtilisateurService utilisateurService;

    UtilisateurRepo utilisateurRepo;

    PasswordEncoder passwordEncoder;

    // Forgot password REST API
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        System.out.println("Received email: " + email);
        utilisateurService.createPasswordResetToken(email);
        return ResponseEntity.ok("Un lien pour réinitialiser votre mot de passe a été envoyé à votre adresse mail");
    }


    // Reset password REST API
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword){
        UtilisateurDTO utilisateurDto = utilisateurService.validatePasswordResetToken(token);
        utilisateurDto.setMotDePasse(passwordEncoder.encode(newPassword));
        utilisateurDto.setResetToken(null);
        utilisateurDto.setTokenExpiration(null);
        utilisateurRepo.save(UtilisateurMapper.mapToUtilisateur(utilisateurDto));
        return ResponseEntity.ok("Mot de passe réinitialisé avec succès");
    }

    // Update password REST API
    @PutMapping("/{id}/changer-mot-de-passe")
    public ResponseEntity<String> changePassword(@PathVariable("id") Long id, @RequestBody ChangePasswordDTO changePasswordDTO){
        try {
            utilisateurService.changePassword(id, changePasswordDTO);
            return ResponseEntity.ok("Mot de passe changé avec succès");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


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

    //get Utilisateurs By roles REST API
    @GetMapping("/roles")
    public ResponseEntity<List<UtilisateurDTO>> getUtilisateursByRoles(
            @RequestParam(value = "roles") List<RoleUtilisateur> roles){
        List<UtilisateurDTO> utilisateurDtos = utilisateurService.getUtilisateursByRole(roles);
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
