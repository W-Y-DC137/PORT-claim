package com.example.PORTClaimApp.Service.ServiceImpl;

import com.example.PORTClaimApp.DTO.TicketDTO;
import com.example.PORTClaimApp.Entity.Ticket;
import com.example.PORTClaimApp.Exception.RessourceNotFoundException;
import com.example.PORTClaimApp.Mapper.*;
import com.example.PORTClaimApp.Repository.*;
import com.example.PORTClaimApp.Service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepo ticketRepo;

   @Autowired
    ReferentielRepo referentielRepo;
    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    UtilisateurRepo utilisateurRepo;
    @Override
    public TicketDTO createTicket(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.mapToTicket(ticketDTO);
        Ticket savedTicket = ticketRepo.save(ticket);
        return TicketMapper.mapToTicketDTO(savedTicket);
    }

    @Override
    public TicketDTO getTicket(Long ticketId) {
        Ticket ticket = ticketRepo.findById(ticketId)
                .orElseThrow(()->
                        new RessourceNotFoundException("Nous n'avons trouvé aucun ticket correspondant à l'ID fourni : " + ticketId + ". Veuillez vérifier l'ID et réessayer")
                        );
        return TicketMapper.mapToTicketDTO(ticket);
    }

    @Override
    public List<TicketDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepo.findAll();
        return tickets.stream().map((ticket) -> TicketMapper.mapToTicketDTO(ticket))
                .collect(Collectors.toList());
    }

   /* @Override
    public List<TicketDTO> getTicketByClient(Long clientId) {
        List<Ticket> tickets = ticketRepo.fin
        return null;
    }

    @Override
    public List<TicketDTO> getTicketByAgent(Long agentId) {
        return null;
    }*/

    @Override
    public TicketDTO updateTicket(Long ticketId, TicketDTO updatedTicketDto) {
        Ticket ticket = ticketRepo.findById(ticketId)
                .orElseThrow(()->
                        new RessourceNotFoundException("Nous n'avons trouvé aucun ticket correspondant à l'ID fourni : " + ticketId + ". Veuillez vérifier l'ID et réessayer")
                );
        ticket.setType(updatedTicketDto.getTypeTicketDto());
        ticket.setTheme(referentielRepo.findById(updatedTicketDto.getThemeId()).orElse(null));
        ticket.setSousTheme(referentielRepo.findById(updatedTicketDto.getSousThemeId()).orElse(null));
        ticket.setNiveauUrgence(updatedTicketDto.getNiveauUrgenceDto());
        ticket.setObjet(updatedTicketDto.getObjetDto());
        ticket.setDescription(updatedTicketDto.getDescriptionDto());
        ticket.setStatutTicket(updatedTicketDto.getStatusDto());
        ticket.setDateOuverture(updatedTicketDto.getDateOuvertureDto());
        ticket.setDateResolution(updatedTicketDto.getDateResolutionDto());
        ticket.setDateFermeture(updatedTicketDto.getDateFermetureDto());
        if(updatedTicketDto.getIdClientDto()!=null){
       ticket.setClient(utilisateurRepo.findById(updatedTicketDto.getIdClientDto()).orElse(null));}
        if(updatedTicketDto.getIdAgentDto()!=null){
       ticket.setAgent(utilisateurRepo.findById(updatedTicketDto.getIdAgentDto()).orElse(null));}
        if(updatedTicketDto.getIdAdminDto()!=null){
        ticket.setAdmin(utilisateurRepo.findById(updatedTicketDto.getIdAdminDto()).orElse(null));}
        Ticket updatedTicket = ticketRepo.save(ticket);
        return TicketMapper.mapToTicketDTO(updatedTicket);
    }

    @Override
    public void deleteTicket(Long ticketId) {
        Ticket ticket = ticketRepo.findById(ticketId)
                .orElseThrow(()->
                        new RessourceNotFoundException("Nous n'avons trouvé aucun ticket correspondant à l'ID fourni : " + ticketId + ". Veuillez vérifier l'ID et réessayer")
                );
        ticketRepo.deleteById(ticketId);
    }
}
