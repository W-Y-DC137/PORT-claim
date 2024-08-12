package com.example.PORTClaimApp.Service;

import com.example.PORTClaimApp.DTO.ThemeDTO;

import java.util.List;

public interface ThemeService {
    ThemeDTO createTheme(ThemeDTO themeDTO);
    ThemeDTO getTheme(Long themeId);
    List<ThemeDTO> getAllThemes();
    ThemeDTO updateTheme(Long themeId,ThemeDTO updatedThemeDTO);
    void deleteTheme(Long themeId);
}
