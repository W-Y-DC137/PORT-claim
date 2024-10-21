package com.example.PORTClaimApp.auth;

import com.example.PORTClaimApp.Entity.Utilisateur;
import com.example.PORTClaimApp.Repository.UtilisateurRepo;
import com.example.PORTClaimApp.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    private final JwtService jwtService;

    private final UtilisateurRepo utilisateurRepo;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest  request
    ){
        return ResponseEntity.ok(service.authenticate(request));

    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(
            @RequestBody Map<String, Long> request
    ) {
        Long userId = request.get("id");
        Utilisateur utilisateur = utilisateurRepo.findById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        String newToken = jwtService.generateToken(utilisateur);
        String firebaseToken = service.generateFirebaseToken(utilisateur);
        return ResponseEntity.ok(AuthenticationResponse.builder()
                .token(newToken)           // JWT token
                .firebaseToken(firebaseToken)  // Firebase token
                .build());
    }

    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile(@RequestBody Utilisateur updatedUser) {
        Utilisateur utilisateur = utilisateurRepo.findById(updatedUser.getId()).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        utilisateur.setNomUtilisateur(updatedUser.getNomUtilisateur());
        utilisateur.setEmail(updatedUser.getEmail());
        utilisateurRepo.save(utilisateur);

        // Generate new token
        String newToken = jwtService.generateToken(utilisateur);
        // Generate new Firebase custom token
        String firebaseToken = service.generateFirebaseToken(utilisateur);
        return ResponseEntity.ok(AuthenticationResponse.builder()
                .token(newToken)              // JWT token
                .firebaseToken(firebaseToken) // Firebase token
                .build());
    }
}
