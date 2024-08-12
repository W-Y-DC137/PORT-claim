package com.example.PORTClaimApp.Service.ServiceImpl;

import com.example.PORTClaimApp.DTO.ThemeDTO;
import com.example.PORTClaimApp.Entity.Theme;
import com.example.PORTClaimApp.Exception.RessourceNotFoundException;
import com.example.PORTClaimApp.Mapper.ThemeMapper;
import com.example.PORTClaimApp.Repository.ThemeRepo;
import com.example.PORTClaimApp.Service.ThemeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ThemeServiceImpl implements ThemeService {
    ThemeRepo themeRepo;
    @Override
    public ThemeDTO createTheme(ThemeDTO themeDTO) {
        Theme theme = ThemeMapper.mapToTheme(themeDTO);
        Theme savedTheme = themeRepo.save(theme);
        return ThemeMapper.mapToThemeDTO(savedTheme);
    }

    @Override
    public ThemeDTO getTheme(Long themeId) {
        Theme theme=themeRepo.findById(themeId)
                .orElseThrow(()->
                        new RessourceNotFoundException("Nous n'avons trouvé aucun theme correspondant à l'ID fourni : " + themeId + ". Veuillez vérifier l'ID et réessayer")
                );

        return ThemeMapper.mapToThemeDTO(theme);
    }

    @Override
    public List<ThemeDTO> getAllThemes() {
        List<Theme> themes= themeRepo.findAll();

        return themes.stream().map((theme) -> ThemeMapper.mapToThemeDTO(theme))
                .collect(Collectors.toList());
    }

    @Override
    public ThemeDTO updateTheme(Long themeId, ThemeDTO updatedThemeDTO) {
        Theme theme=themeRepo.findById(themeId)
                .orElseThrow(()->
                        new RessourceNotFoundException("Nous n'avons trouvé aucun theme correspondant à l'ID fourni : " + themeId + ". Veuillez vérifier l'ID et réessayer")
                        );
        theme.setTheme(updatedThemeDTO.getThemeDto());
        Theme updatedTheme = themeRepo.save(theme);
        return ThemeMapper.mapToThemeDTO(updatedTheme);
    }

    @Override
    public void deleteTheme(Long themeId) {
        Theme theme=themeRepo.findById(themeId)
                .orElseThrow(()->
                        new RessourceNotFoundException("Nous n'avons trouvé aucun theme correspondant à l'ID fourni : " + themeId + ". Veuillez vérifier l'ID et réessayer")
                );
        themeRepo.deleteById(themeId);

    }
}
