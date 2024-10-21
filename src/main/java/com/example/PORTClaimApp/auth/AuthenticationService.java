package com.example.PORTClaimApp.auth;

import com.example.PORTClaimApp.Entity.Utilisateur;
import com.example.PORTClaimApp.Repository.UtilisateurRepo;
import com.example.PORTClaimApp.config.JwtService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    //private final Utilisateur utilisateur;

    private final UtilisateurRepo utilisateurRepo;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = Utilisateur.builder()
                .nomUtilisateur(request.getUsername())
                .email(request.getEmail())
                .role(request.getRole())
                .motDePasse(passwordEncoder.encode(request.getPassword()))
                .build();
        Utilisateur utilisateur = utilisateurRepo.save(user);
        var jwtToken = jwtService.generateToken(utilisateur);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = utilisateurRepo.findByNomUtilisateur(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        String firebaseToken = generateFirebaseToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .firebaseToken(firebaseToken)
                .build();
    }
    String generateFirebaseToken(Utilisateur user) {
        try {
            return FirebaseAuth.getInstance().createCustomToken(String.valueOf(user.getId()));
        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Firebase token generation failed", e);
        }
    }
}
