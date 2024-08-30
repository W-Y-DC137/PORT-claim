package com.example.PORTClaimApp.Service.ServiceImpl;

import com.example.PORTClaimApp.DTO.ChangePasswordDTO;
import com.example.PORTClaimApp.DTO.UtilisateurDTO;
import com.example.PORTClaimApp.Entity.Utilisateur;
import com.example.PORTClaimApp.Enums.RoleUtilisateur;
import com.example.PORTClaimApp.Exception.RessourceNotFoundException;
import com.example.PORTClaimApp.Mapper.UtilisateurMapper;
import com.example.PORTClaimApp.Repository.UtilisateurRepo;
import com.example.PORTClaimApp.Service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {
    @Autowired
    UtilisateurRepo utilisateurRepo;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

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
    public List<UtilisateurDTO> getUtilisateursByRole(List<RoleUtilisateur> roles) {
        List<Utilisateur> utilisateurs = utilisateurRepo.findByRoleIn(roles);
        return utilisateurs.stream().map((utilisteur)->UtilisateurMapper.mapToUtilisateurDto(utilisteur))
                .collect(Collectors.toList());
    }

    @Override
    public void changePassword(Long userId, ChangePasswordDTO changePasswordDTO) {
     Utilisateur utilisateur =utilisateurRepo.findById(userId)
             .orElseThrow(()->
                     new RessourceNotFoundException("\"Nous n'avons trouvé aucun utilisateur correspondant à l'ID fourni : \" + id + \". Veuillez vérifier l'ID et réessayer")
                     );
     if(!passwordEncoder.matches(changePasswordDTO.getCurrentPassword(), utilisateur.getMotDePasse())){
         throw new RuntimeException("Current password is incorrect");
     }
     if(!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmNewPassword())){
         throw new RuntimeException("New password and confirmation do not match");
     }

     utilisateur.setMotDePasse((passwordEncoder.encode(changePasswordDTO.getNewPassword())));
     utilisateurRepo.save(utilisateur);


    }

  /*  @Override
    public void createPasswordResetToken(String email) {
        Utilisateur utilisateur = utilisateurRepo.findByEmail(email)
                .orElseThrow(()->
                                new RessourceNotFoundException("Nous n'avons trouvé aucun utilisateur correspondant à l'email fourni : " + email + ". Veuillez vérifier l'ID et réessayer")
                        );
        //UtilisateurDTO utilisateurDto = UtilisateurMapper.mapToUtilisateurDto(utilisateur);

        String token = UUID.randomUUID().toString();
        utilisateur.setResetToken(token);
        utilisateur.setTokenExpiration(LocalDateTime.now().plusHours(1));//token ayb9a salh lmodate sa3a

        sendPasswordResetEmail(utilisateur.getEmail(),token);
    }*/

/*    @Override
    public void sendPasswordResetEmail(String email, String token) {
        String url = "http://localhost:3000/reset-password?token=" + token;
        String subject = "demande de rénitialisation de mot de passe";
        String body = "Cliquer sur le lien ci dessous pour rénitialiser votre mot de passe"+url;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }*/

    @Override
    public UtilisateurDTO updateUtilisateur(Long id, UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = utilisateurRepo.findById(id)
                .orElseThrow(()->
                        new RessourceNotFoundException("Nous n'avons trouvé aucun utilisateur correspondant à l'ID fourni : " + id + ". Veuillez vérifier l'ID et réessayer")
                );
        utilisateur.setNomUtilisateur(utilisateurDTO.getNomUtilisateur());
        utilisateur.setEmail(utilisateurDTO.getEmail());
        utilisateur.setRole(utilisateurDTO.getRole());
        //utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurDTO.getMotDePasse()));
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

    @Override
    public void updateResetPasswordToken(String token, String email) {
        Utilisateur utilisateur = utilisateurRepo.findByEmail(email)
                .orElseThrow(()->
                                new RessourceNotFoundException("Nous n'avons trouvé aucun utilisateur avec l'email : " + email + ". Veuillez vérifier l'email et réessayer")
                        );
        if(utilisateur != null){
            utilisateur.setResetPasswordToken(token);
            utilisateurRepo.save(utilisateur);
        }
    }

    @Override
    public Utilisateur getByResetPasswordToken(String token) {
        return utilisateurRepo.findByResetPasswordToken(token)
                .orElseThrow(()->
                        new RessourceNotFoundException("token invalide")
                        );
    }

    @Override
    public void updatePassword(Utilisateur utilisateur, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        utilisateur.setMotDePasse(encodedPassword);
        utilisateur.setResetPasswordToken(null);
        utilisateurRepo.save(utilisateur);
    }




/*    @Override
    public Utilisateur validatePasswordResetToken(String token) {
        Utilisateur utilisateur = utilisateurRepo.findByResetToken(token)
                .orElseThrow(()->
                        new RessourceNotFoundException("token non valide")
                        );
       // UtilisateurDTO utilisateurDto = UtilisateurMapper.mapToUtilisateurDto(utilisateur);

        if(utilisateur.getTokenExpiration().isBefore(LocalDateTime.now())){
            throw new RuntimeException("Token expiré");
        }
        return utilisateur;
    }*/
}
