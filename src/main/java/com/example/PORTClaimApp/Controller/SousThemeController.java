package com.example.PORTClaimApp.Controller;

import com.example.PORTClaimApp.DTO.SousThemeDTO;
import com.example.PORTClaimApp.Entity.SousTheme;
import com.example.PORTClaimApp.Service.SousThemeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("api/sousThemes")
public class SousThemeController {
    SousThemeService sousThemeService;

    //create Sous Theme REST API
    @PostMapping
    public ResponseEntity<SousThemeDTO> createSousTheme(@RequestBody SousThemeDTO sousThemeDto){
        SousThemeDTO savedSousTheme = sousThemeService.createSousTheme(sousThemeDto);
        return new ResponseEntity<>(savedSousTheme, HttpStatus.CREATED);
    }

    //get Sous Theme REST API
    @GetMapping("{id}")
    public ResponseEntity<SousThemeDTO> getSousThemeById(@PathVariable("id") Long sousThemeId){
        SousThemeDTO sousThemeDto = sousThemeService.getSousTheme(sousThemeId);
        return ResponseEntity.ok(sousThemeDto);
    }

    //get all Sous Themes REST API
    @GetMapping
    public ResponseEntity<List<SousThemeDTO>> getAllSousThemes(){
        List<SousThemeDTO> sousThemeDtos = sousThemeService.getAllSousTheme();
        return ResponseEntity.ok(sousThemeDtos);
    }

    //update Sous Theme REST API
    @PutMapping("{id}")
    public ResponseEntity<SousThemeDTO> updateSousTheme(@PathVariable("id") Long sousThemeId ,@RequestBody SousThemeDTO updatedSousTheme){
        SousThemeDTO sousThemeDto = sousThemeService.updateSousTheme(sousThemeId,updatedSousTheme);
        return ResponseEntity.ok(sousThemeDto);
    }

    //delete Sous Theme REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSousTheme(@PathVariable("id") Long sousThemeId){
        sousThemeService.deleteSousTheme(sousThemeId);
        return ResponseEntity.ok("sous theme supprimé avec succé");
    }
}
