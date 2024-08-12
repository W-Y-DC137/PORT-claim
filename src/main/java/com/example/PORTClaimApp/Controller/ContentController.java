package com.example.PORTClaimApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

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

 /*   @GetMapping("/alo/login")
    public String getLoginPage(){ return "PageLogin";}*/
}
