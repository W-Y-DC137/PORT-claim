package com.example.PORTClaimApp.Controller;

import com.example.PORTClaimApp.DTO.ChangePasswordDTO;
import com.example.PORTClaimApp.DTO.UtilisateurDTO;
import com.example.PORTClaimApp.Entity.Utilisateur;
import com.example.PORTClaimApp.Enums.RoleUtilisateur;
import com.example.PORTClaimApp.Repository.UtilisateurRepo;
import com.example.PORTClaimApp.Service.UtilisateurService;
import com.example.PORTClaimApp.config.Utility;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin("*")

public class UtilisateurController {
    @Autowired
    UtilisateurService utilisateurService;

    UtilisateurRepo utilisateurRepo;

    @Autowired
    private JavaMailSender mailSender;

    private final PasswordEncoder passwordEncoder;

    /*// Forgot password REST API
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        System.out.println("Received email: " + email);
        utilisateurService.createPasswordResetToken(email);
        return ResponseEntity.ok("Un lien pour réinitialiser votre mot de passe a été envoyé à votre adresse mail");
    }*/
    /*// Reset password REST API
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword){
        Utilisateur utilisateur = utilisateurService.validatePasswordResetToken(token);
        utilisateur.setMotDePasse(passwordEncoder.encode(newPassword));
        utilisateur.setResetToken(null);
        utilisateur.setTokenExpiration(null);
       // utilisateurRepo.save(UtilisateurMapper.mapToUtilisateur(utilisateur));
        return ResponseEntity.ok("Mot de passe réinitialisé avec succès");
    }*/

    //forgot password REST API
    @GetMapping("/forgot_password")
    public String showForgotPasswordForm(){
        return "forgot_password_form";
    }

    /*@PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request , Model model){
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();

        try{
            utilisateurService.updateResetPasswordToken(token,email);
            String resetPassswordLink = Utility.getSiteURL(request) +"/reset_password?token="+token;
            sendEmail(email,resetPassswordLink);
            model.addAttribute("message","on a envoyé un lien pour la rénitialisation de votre mot de passe , vérifier votre boîte mail");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "forgot_password_form";
    }*/

    @PostMapping("/forgot_password")
    public ResponseEntity<String> processForgotPassword(@RequestParam String email, HttpServletRequest request) {
        System.out.println("email received: " + email);
        String token = UUID.randomUUID().toString();

        try {
            utilisateurService.updateResetPasswordToken(token, email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            return ResponseEntity.ok("A password reset link has been sent to your email: " + email);
        } catch (Exception e) {
            e.printStackTrace(); // Log the full stack trace for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing your request.");
        }
    }



    public void sendEmail(String recipientEmail, String link) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

            helper.setFrom("no-reply@portnet.ma", "Portnet HelpDesk");
            helper.setTo(recipientEmail);
            helper.setSubject("Here's the link to reset your password");

            String content = "<p>Hello,</p>"
                    + "<p>You have requested to reset your password.</p>"
                    + "<p>Click the link below to change your password:</p>"
                    + "<p><a href=\"" + link + "\">Change my password</a></p>"
                    + "<br>"
                    + "<p>Ignore this email if you do remember your password, "
                    + "or you have not made the request.</p>";

            helper.setText(content, true);

            mailSender.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace(); // Log or handle the exception
            throw new RuntimeException("Failed to send email", e);
        }
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value="token") String token , Model model){
        Utilisateur utilisateur = utilisateurService.getByResetPasswordToken(token);
        model.addAttribute("token",token);

        if(utilisateur == null){
            model.addAttribute("message","token non valide");
            return "message";
        }
        return "reset_password_form";
    }

    /*@PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request , Model model){
        String token = request.getParameter("token");
        String password= request.getParameter("password");

        Utilisateur utilisateur = utilisateurService.getByResetPasswordToken(token);
        model.addAttribute("title","Reset your password");

        if(utilisateur == null){
            model.addAttribute("message","token non valide");
            return "message";
        }else {
            utilisateurService.updatePassword(utilisateur,password);
            model.addAttribute("message","mot de passe changé avec succé");
        }
        return "message";
    }*/
    @PostMapping("/reset_password")
    public ResponseEntity<String> processResetPassword(@RequestParam String token, @RequestParam String password) {
        try {
            Utilisateur utilisateur = utilisateurService.getByResetPasswordToken(token);
            if (utilisateur == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token.");
            } else {
                utilisateurService.updatePassword(utilisateur, password);
                return ResponseEntity.ok("Your password has been successfully reset.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred.");
        }
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
