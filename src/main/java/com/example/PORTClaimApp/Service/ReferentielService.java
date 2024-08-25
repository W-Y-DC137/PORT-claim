package com.example.PORTClaimApp.Service;

import com.example.PORTClaimApp.DTO.ReferentielDTO;

import java.sql.Ref;
import java.util.List;

public interface ReferentielService {
    ReferentielDTO createReferentiel(ReferentielDTO referentielDTO);

    ReferentielDTO getReferentiel(Long id);

    List<ReferentielDTO> getAll();

    ReferentielDTO updateReferentiel(Long id , ReferentielDTO referentielDto);

    void deleteById(Long id);


}
