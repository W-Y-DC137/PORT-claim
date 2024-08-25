package com.example.PORTClaimApp.Controller;

import com.example.PORTClaimApp.DTO.ReferentielDTO;
import com.example.PORTClaimApp.Service.ReferentielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/referentiel")
public class ReferentielController {


    @Autowired
    ReferentielService referentielService;

    //Create Reference REST API
    @PostMapping
    public ResponseEntity<ReferentielDTO> createReference(@RequestBody ReferentielDTO referentielDto){
        ReferentielDTO savedReference = referentielService.createReferentiel(referentielDto);
        return new ResponseEntity<>(savedReference,HttpStatus.CREATED);
    }

    //getById Reference REST API
    @GetMapping("{id}")
    public ResponseEntity<ReferentielDTO> getRefrenceById(@PathVariable("id") Long id){
        ReferentielDTO referentielDto = referentielService.getReferentiel(id);
        return ResponseEntity.ok(referentielDto);
    }

    //GetAllReferences REST API
    @GetMapping
    public ResponseEntity<List<ReferentielDTO>> getAllReferences(){
        List<ReferentielDTO> referentielDtos = referentielService.getAll();
        return  ResponseEntity.ok(referentielDtos);
    }

    //UpdateReference REST API
    @PutMapping("{id}")
    public ResponseEntity<ReferentielDTO> updateReference(@PathVariable("id") Long id , @RequestBody ReferentielDTO referentielDto){
        ReferentielDTO updatedReference = referentielService.updateReferentiel(id,referentielDto);
        return ResponseEntity.ok(updatedReference);
    }

    //DeleteReferenceById REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteReferenceById(@PathVariable("id") Long id){
        referentielService.deleteById(id);
        return ResponseEntity.ok("Reference supprimé avec succé");
    }


}
