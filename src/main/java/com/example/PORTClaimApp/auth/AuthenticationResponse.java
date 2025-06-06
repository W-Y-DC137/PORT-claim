package com.example.PORTClaimApp.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;          // Your JWT token
    private String firebaseToken;  // Firebase Custom Token
}
