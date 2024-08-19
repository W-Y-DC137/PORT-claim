package com.example.PORTClaimApp.DTO;

import com.example.PORTClaimApp.Enums.SousThemeListe;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SousThemeDTO {
    private Long idSousThemeDto;

    private String sousThemeDto;

    private Long themeDtoId;
}
