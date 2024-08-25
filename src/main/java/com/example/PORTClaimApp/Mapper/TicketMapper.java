package com.example.PORTClaimApp.Mapper;

import com.example.PORTClaimApp.DTO.TicketDTO;
import com.example.PORTClaimApp.Entity.SousTheme;
import com.example.PORTClaimApp.Entity.Ticket;
import com.example.PORTClaimApp.Entity.Utilisateur;
import com.example.PORTClaimApp.Repository.*;
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
    @Autowired
    ReferentielRepo referentielRepo;
        public static TicketDTO mapToTicketDTO (Ticket ticket){
        if (ticket==null ){
            return null;
        }
        Long agentId = ticket.getAgent() != null ? ticket.getAgent().getId() : null;
        Long adminId = ticket.getAdmin() != null ? ticket.getAdmin().getId() : null;
        return new TicketDTO(
                ticket.getIdTicket(),
                ticket.getType(),
                ticket.getTheme().getId(),
                ticket.getSousTheme().getId(),
                ticket.getNiveauUrgence(),
                ticket.getObjet(),
                ticket.getDescription(),
                ticket.getStatutTicket(),
                ticket.getDateOuverture(),
                ticket.getDateResolution(),
                ticket.getDateFermeture(),
                ticket.getClient().getId(),
                agentId,
                adminId
        );

    }


    public Ticket mapToTicket(TicketDTO ticketDTO) {
        if (ticketDTO == null) {
            return null;
        }

        Ticket ticket = new Ticket();
        ticket.setIdTicket(ticketDTO.getIdTicketDto());
        ticket.setType(ticketDTO.getTypeTicketDto());
        ticket.setTheme(referentielRepo.findById(ticketDTO.getThemeId()).orElse(null));
        ticket.setSousTheme(referentielRepo.findById(ticketDTO.getSousThemeId()).orElse(null));
        ticket.setNiveauUrgence(ticketDTO.getNiveauUrgenceDto());
        ticket.setObjet(ticketDTO.getObjetDto());
        ticket.setDescription(ticketDTO.getDescriptionDto());
        ticket.setStatutTicket(ticketDTO.getStatusDto());
        ticket.setDateOuverture(ticketDTO.getDateOuvertureDto());
        ticket.setDateResolution(ticketDTO.getDateResolutionDto());
        ticket.setDateFermeture(ticketDTO.getDateFermetureDto());

        // Set client
        if (ticketDTO.getIdClientDto() != null) {
            ticket.setClient(utilisateurRepo.findById(ticketDTO.getIdClientDto()).orElse(null));
        }

        // Set agent only if the ID is not null
        if (ticketDTO.getIdAgentDto() != null) {
            ticket.setAgent(utilisateurRepo.findById(ticketDTO.getIdAgentDto()).orElse(null));
        }

        // Set admin only if the ID is not null
        if (ticketDTO.getIdAdminDto() != null) {
            ticket.setAdmin(utilisateurRepo.findById(ticketDTO.getIdAdminDto()).orElse(null));
        }

        return ticket;
    }

}
