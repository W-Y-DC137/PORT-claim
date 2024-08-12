package com.example.PORTClaimApp.Mapper;

import com.example.PORTClaimApp.DTO.SousThemeDTO;
import com.example.PORTClaimApp.Entity.SousTheme;
import org.springframework.stereotype.Component;

@Component
public class SousThemeMapper {
    public static SousThemeDTO mapToSousThemeDTO(SousTheme sousTheme){
        return new SousThemeDTO(
                sousTheme.getIdSousTheme(),
                sousTheme.getSousTheme(),
                ThemeMapper.mapToThemeDTO(sousTheme.getTheme())
        );
    }

    public  static SousTheme mapToSousTheme(SousThemeDTO sousThemeDTO){
        return new SousTheme(
                sousThemeDTO.getIdSousThemeDto(),
                sousThemeDTO.getSousThemeDto(),
                ThemeMapper.mapToTheme(sousThemeDTO.getThemeDTO())
        );
    }
}
