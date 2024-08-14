package com.example.PORTClaimApp.auth;

import com.example.PORTClaimApp.Enums.RoleUtilisateur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;

    private String email;

    private RoleUtilisateur role;
    private String password;


}
