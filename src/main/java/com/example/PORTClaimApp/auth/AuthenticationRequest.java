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
public class AuthenticationRequest {

    private String username ;
     private String password;

     //private String role;


}
