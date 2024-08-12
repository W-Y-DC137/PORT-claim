package com.example.PORTClaimApp.Mapper;

import com.example.PORTClaimApp.DTO.TicketDTO;
import com.example.PORTClaimApp.Entity.SousTheme;
import com.example.PORTClaimApp.Entity.Ticket;
import com.example.PORTClaimApp.Entity.Utilisateur;
import com.example.PORTClaimApp.Repository.UtilisateurRepo;
import com.example.PORTClaimApp.Service.ServiceImpl.UtilisateurServiceImpl;
import com.example.PORTClaimApp.Service.UtilisateurService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class TicketMapper {
    @Autowired
  UtilisateurRepo utilisateurRepo;
    public static TicketDTO mapToTicketDTO (Ticket ticket){
        if (ticket==null ){
            return null;
        }
        return new TicketDTO(
                ticket.getIdTicket(),
                ticket.getType(),
                ThemeMapper.mapToThemeDTO(ticket.getTheme()),
                SousThemeMapper.mapToSousThemeDTO(ticket.getSousTheme()),
                ticket.getNiveauUrgence(),
                ticket.getObjet(),
                ticket.getDescription(),
                ticket.getStatutTicket(),
                ticket.getDateOuverture(),
                ticket.getDateResolution(),
                ticket.getDateFermeture(),
                ticket.getClient().getId(),
                ticket.getAgent().getId(),
                ticket.getAdmin().getId()
        );

    }


    public  Ticket mapToTicket (TicketDTO ticketDTO){
        if(ticketDTO==null){
            return null;
        }
        Ticket ticket = new Ticket();
        ticket.setIdTicket(ticketDTO.getIdTicketDto());
        ticket.setType(ticketDTO.getTypeTicketDto());
        ticket.setTheme(ThemeMapper.mapToTheme(ticketDTO.getThemeDTO()));
        ticket.setSousTheme((SousThemeMapper.mapToSousTheme(ticketDTO.getSousThemeDTO())));
        ticket.setNiveauUrgence(ticketDTO.getNiveauUrgenceDto());
        ticket.setObjet(ticketDTO.getObjetDto());
        ticket.setDescription(ticketDTO.getDescriptionDto());
        ticket.setStatutTicket(ticketDTO.getStatusDto());
        ticket.setDateOuverture(ticketDTO.getDateOuvertureDto());
        ticket.setDateResolution(ticketDTO.getDateResolutionDto());
        ticket.setDateFermeture(ticketDTO.getDateFermetureDto());
        if(ticketDTO.getIdClientDto()!=null){
            ticket.setClient(utilisateurRepo.findById(ticketDTO.getIdClientDto()).orElse(null));
        }

        if(ticketDTO.getIdAgentDto()!=null){
            ticket.setAgent(utilisateurRepo.findById(ticketDTO.getIdAgentDto()).orElse(null));
        }
        if(ticketDTO.getIdAdminDTO()!=null){
            ticket.setAdmin(utilisateurRepo.findById(ticketDTO.getIdAdminDTO()).orElse(null));
        }
        return ticket;
    }
}
