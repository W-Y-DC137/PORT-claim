package com.example.PORTClaimApp.Service;

import com.example.PORTClaimApp.DTO.SousThemeDTO;

import java.util.List;

public interface SousThemeService {
    SousThemeDTO createSousTheme(SousThemeDTO sousThemeDTO);
    SousThemeDTO getSousTheme(Long sousThemeId);
    List<SousThemeDTO> getAllSousTheme();
    SousThemeDTO updateSousTheme(Long sousThemeId,SousThemeDTO updatedSousThemeDto);
    void deleteSousTheme(Long sousThemeId);
}
