package com.example.PORTClaimApp.Service;

import com.example.PORTClaimApp.DTO.ChangePasswordDTO;
import com.example.PORTClaimApp.DTO.UtilisateurDTO;
import com.example.PORTClaimApp.Entity.Ticket;
import com.example.PORTClaimApp.Entity.Utilisateur;
import com.example.PORTClaimApp.Enums.RoleUtilisateur;

import java.util.List;

public interface UtilisateurService {
   // Utilisateur createById(Long id);
    UtilisateurDTO createUtilisateur(UtilisateurDTO utilisateurDTO);
    UtilisateurDTO getUtilisateur(Long id);
    List<UtilisateurDTO> getAllUtilisateurs();
    List<UtilisateurDTO> getUtilisateursByRole(List<RoleUtilisateur> roles);

    void changePassword(Long userId , ChangePasswordDTO changePasswordDTO);
   /* void createPasswordResetToken(String email);
    void sendPasswordResetEmail(String email, String token);*/
    UtilisateurDTO updateUtilisateur(Long id ,UtilisateurDTO utilisateurDTO);
    void deleteUtilisateur(Long id);

    public void updateResetPasswordToken(String token , String email);

    public Utilisateur getByResetPasswordToken(String token);

    public void updatePassword(Utilisateur utilisateur,String newPassword);


 /*Utilisateur validatePasswordResetToken(String token);*/


}
