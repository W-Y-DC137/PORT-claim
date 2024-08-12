package com.example.PORTClaimApp.Entity;


import com.example.PORTClaimApp.Enums.SousThemeListe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class SousTheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSousTheme;


    private String  sousTheme;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;
}
