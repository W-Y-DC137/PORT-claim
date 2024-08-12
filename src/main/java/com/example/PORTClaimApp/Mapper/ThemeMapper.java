package com.example.PORTClaimApp.Mapper;

import com.example.PORTClaimApp.DTO.ThemeDTO;
import com.example.PORTClaimApp.Entity.Theme;
import org.springframework.stereotype.Component;

@Component
public class ThemeMapper {
 public static ThemeDTO mapToThemeDTO(Theme theme){
    return new ThemeDTO(
      theme.getIdTheme(),
      theme.getTheme()
     );
 }

 public static Theme mapToTheme(ThemeDTO themeDTO){
     return new Theme(
       themeDTO.getIdThemeDto(),
       themeDTO.getThemeDto()
     );
 }
}
