package com.example.PORTClaimApp.Mapper;

import com.example.PORTClaimApp.DTO.ReferentielDTO;
import com.example.PORTClaimApp.Entity.Referentiel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface ReferentielMapper {
    ReferentielMapper INSTANCE = Mappers.getMapper(ReferentielMapper.class);
    ReferentielDTO toReferentielDTO(Referentiel referentiel);
    Referentiel toReferentiel(ReferentielDTO referentielDto);
   /* List<ReferentielDTO> toReferentielDTOs(List<Referentiel> referentiels);
    List<Referentiel> toReferentiels(List<ReferentielDTO> referentielDtos);*/
}
