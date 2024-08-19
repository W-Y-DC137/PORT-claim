package com.example.PORTClaimApp.Mapper;

import com.example.PORTClaimApp.DTO.SousThemeDTO;
import com.example.PORTClaimApp.Entity.SousTheme;
import com.example.PORTClaimApp.Repository.ThemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SousThemeMapper {
    @Autowired
    ThemeRepo themeRepo;

    public static SousThemeDTO mapToSousThemeDTO(SousTheme sousTheme){
        return new SousThemeDTO(
                sousTheme.getIdSousTheme(),
                sousTheme.getSousTheme(),
                sousTheme.getTheme().getIdTheme()
        );
    }

    public   SousTheme mapToSousTheme(SousThemeDTO sousThemeDTO){
        return new SousTheme(
                sousThemeDTO.getIdSousThemeDto(),
                sousThemeDTO.getSousThemeDto(),
                themeRepo.findById(sousThemeDTO.getThemeDtoId()).orElse(null)
        );
    }
}
