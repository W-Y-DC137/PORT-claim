package com.example.PORTClaimApp.Service.ServiceImpl;

import com.example.PORTClaimApp.DTO.SousThemeDTO;
import com.example.PORTClaimApp.Entity.SousTheme;
import com.example.PORTClaimApp.Exception.RessourceNotFoundException;
import com.example.PORTClaimApp.Mapper.SousThemeMapper;
import com.example.PORTClaimApp.Mapper.ThemeMapper;
import com.example.PORTClaimApp.Repository.SousThemeRepo;
import com.example.PORTClaimApp.Service.SousThemeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SousThemeServiceImpl implements SousThemeService {
    SousThemeRepo sousThemeRepo;
    @Override
    public SousThemeDTO createSousTheme(SousThemeDTO sousThemeDTO) {
        SousTheme sousTheme = SousThemeMapper.mapToSousTheme(sousThemeDTO);
        SousTheme savedSousTheme = sousThemeRepo.save(sousTheme);
        return SousThemeMapper.mapToSousThemeDTO(savedSousTheme);
    }

    @Override
    public SousThemeDTO getSousTheme(Long sousThemeId) {
        SousTheme sousTheme = sousThemeRepo.findById(sousThemeId)
                .orElseThrow(()->
                        new RessourceNotFoundException("Nous n'avons trouvé aucun sous theme correspondant à l'ID fourni : " + sousThemeId + ". Veuillez vérifier l'ID et réessayer")
                );

        return SousThemeMapper.mapToSousThemeDTO(sousTheme);
    }

    @Override
    public List<SousThemeDTO> getAllSousTheme() {
        List<SousTheme> sousThemes = sousThemeRepo.findAll();
        return sousThemes.stream().map((sousTheme)-> SousThemeMapper.mapToSousThemeDTO(sousTheme))
                .collect(Collectors.toList());
    }

    @Override
    public SousThemeDTO updateSousTheme(Long sousThemeId, SousThemeDTO updatedSousThemeDto) {
        SousTheme sousTheme = sousThemeRepo.findById(sousThemeId)
                .orElseThrow(()->
                        new RessourceNotFoundException("Nous n'avons trouvé aucun sous theme correspondant à l'ID fourni : " + sousThemeId + ". Veuillez vérifier l'ID et réessayer")
                );
        sousTheme.setSousTheme(updatedSousThemeDto.getSousThemeDto());
        sousTheme.setTheme(ThemeMapper.mapToTheme(updatedSousThemeDto.getThemeDTO()));

        SousTheme updatedSousTheme = sousThemeRepo.save(sousTheme);

        return SousThemeMapper.mapToSousThemeDTO(updatedSousTheme);
    }

    @Override
    public void deleteSousTheme(Long sousThemeId) {
        SousTheme sousTheme = sousThemeRepo.findById(sousThemeId)
                .orElseThrow(()->
                        new RessourceNotFoundException("Nous n'avons trouvé aucun sous theme correspondant à l'ID fourni : " + sousThemeId + ". Veuillez vérifier l'ID et réessayer")
                );
        sousThemeRepo.deleteById(sousThemeId);

    }
}
