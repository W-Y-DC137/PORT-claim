package com.example.PORTClaimApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentController {
    @Autowired


    @GetMapping("/admin")
    public String getAdminPage() {
        return "PageAdmin.html";
    }

    @GetMapping("/agent")
    public String getAgentPage() {
        return "PageAgent.html";
    }

    @GetMapping("/client")
    public String getClientPage() {
        return "PageClient.html";
    }
    @GetMapping("/log")
    public String log() {
        return "Login";
    }

    /*@PostMapping("/connecter")
    public String loginToken(@RequestBody LoginForm loginForm){
       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
               loginForm.username(),loginForm.password()
       ));
       if(authentication.isAuthenticated()){
           return jwtService.generateToken(utilisateurDetailService.loadUserByUsername(loginForm.username()));
       } else {
           throw new UsernameNotFoundException("Invalid credentials");
       }

    }*/
}
