package com.example.PORTClaimApp.Mapper;

import com.example.PORTClaimApp.DTO.ReferentielDTO;
import com.example.PORTClaimApp.DTO.TicketDTO;
import com.example.PORTClaimApp.Entity.Referentiel;
import com.example.PORTClaimApp.Entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface TicketNewMapper {
    TicketNewMapper INSTANCE = Mappers.getMapper(TicketNewMapper.class);
    TicketDTO toReferentielDTO(Ticket ticket);
    Ticket toReferentiel(TicketDTO ticketDto);
   /* List<ReferentielDTO> toReferentielDTOs(List<Referentiel> referentiels);
    List<Referentiel> toReferentiels(List<ReferentielDTO> referentielDtos);*/
}
