package com.example.PORTClaimApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan("com.example.PORTClaimApp")
public class PortClaimAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortClaimAppApplication.class, args);
	}



}
