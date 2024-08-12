package com.example.PORTClaimApp.Controller;

import com.example.PORTClaimApp.DTO.ThemeDTO;
import com.example.PORTClaimApp.Service.ThemeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/themes")
public class ThemeController {
    ThemeService themeService;

    //Create Theme REST API
    @PostMapping
    public ResponseEntity<ThemeDTO> createTheme(@RequestBody ThemeDTO themeDto){
        ThemeDTO savedTheme = themeService.createTheme(themeDto);
        return new ResponseEntity<>(savedTheme,HttpStatus.CREATED);
    }

    //Get Theme REST API
    @GetMapping("{id}")
    public ResponseEntity<ThemeDTO> getThemeById(@PathVariable("id") Long themeId){
        ThemeDTO themeDto = themeService.getTheme(themeId);
        return ResponseEntity.ok(themeDto);
    }

    //Get All Themes REST API

    @GetMapping
    public ResponseEntity<List<ThemeDTO>> getAllThemes(){
        List<ThemeDTO> themeDtos = themeService.getAllThemes();
        return ResponseEntity.ok(themeDtos);
    }

    //Update Theme REST API
    @PutMapping("{id}")
    public ResponseEntity<ThemeDTO> updateTheme(@PathVariable("id") Long themeId ,@RequestBody ThemeDTO updatedTheme){
        ThemeDTO themeDto = themeService.updateTheme(themeId,updatedTheme);
        return ResponseEntity.ok(themeDto);
    }

    //Delete Theme REST API

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTheme(@PathVariable("id") Long themeId){
        themeService.deleteTheme(themeId);
        return ResponseEntity.ok("Theme supprimé avec succé");
    }
}
