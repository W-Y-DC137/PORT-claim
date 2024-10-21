package com.example.PORTClaimApp.Service.ServiceImpl;

import com.example.PORTClaimApp.DTO.TicketDTO;
import com.example.PORTClaimApp.Entity.Ticket;
import com.example.PORTClaimApp.Entity.Utilisateur;
import com.example.PORTClaimApp.Enums.StatutTicket;
import com.example.PORTClaimApp.Exception.RessourceNotFoundException;
import com.example.PORTClaimApp.Mapper.*;
import com.example.PORTClaimApp.Repository.*;
import com.example.PORTClaimApp.Service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
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
    public List<TicketDTO> getTicketsByClientId(Long clientId) {
        Utilisateur client = utilisateurRepo.findById(clientId).orElseThrow(
                ()->
                new RessourceNotFoundException("aucun client est trouvé avec l'id :"+clientId)

                );

        List<Ticket> tickets = ticketRepo.findByClient(client);

        return tickets.stream().map((ticket)->TicketMapper.mapToTicketDTO(ticket))
                .collect(Collectors.toList());
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
                .orElseThrow(() ->
                        new RessourceNotFoundException("Nous n'avons trouvé aucun ticket correspondant à l'ID fourni : " + ticketId + ". Veuillez vérifier l'ID et réessayer")
                );

        // Met à jour les autres champs du ticket comme avant
        ticket.setType(updatedTicketDto.getTypeTicketDto());
        ticket.setTheme(referentielRepo.findById(updatedTicketDto.getThemeId()).orElse(null));
        ticket.setSousTheme(referentielRepo.findById(updatedTicketDto.getSousThemeId()).orElse(null));
        ticket.setNiveauUrgence(updatedTicketDto.getNiveauUrgenceDto());
        ticket.setObjet(updatedTicketDto.getObjetDto());
        ticket.setDescription(updatedTicketDto.getDescriptionDto());
        ticket.setDateOuverture(updatedTicketDto.getDateOuvertureDto());
        ticket.setDateResolution(updatedTicketDto.getDateResolutionDto());
        ticket.setDateFermeture(updatedTicketDto.getDateFermetureDto());

        // Met à jour les relations avec client, agent, admin si elles existent
        if (updatedTicketDto.getIdClientDto() != null) {
            ticket.setClient(utilisateurRepo.findById(updatedTicketDto.getIdClientDto()).orElse(null));
        }
        if (updatedTicketDto.getIdAgentDto() != null) {
            ticket.setAgent(utilisateurRepo.findById(updatedTicketDto.getIdAgentDto()).orElse(null));
        }
        if (updatedTicketDto.getIdAdminDto() != null) {
            ticket.setAdmin(utilisateurRepo.findById(updatedTicketDto.getIdAdminDto()).orElse(null));
        }

        // Vérifie si le statut a changé
        if (updatedTicketDto.getStatusDto() != null &&
                !updatedTicketDto.getStatusDto().equals(ticket.getStatutTicket())) {

            // Si le statut a changé, on met à jour la date du dernier statut
            ticket.setStatutTicket(updatedTicketDto.getStatusDto());
            ticket.setDateDernierStatut(new Date());  // Met à jour la date du dernier changement de statut
        }

        // Sauvegarde le ticket mis à jour
        Ticket updatedTicket = ticketRepo.save(ticket);

        // Retourne le DTO mis à jour
        return TicketMapper.mapToTicketDTO(updatedTicket);
    }

   /* @Override
    public void updateTicketStatus(Ticket ticket, StatutTicket nouveauStatut) {
        ticket.setStatutTicket(nouveauStatut);
        ticket.setDateDernierStatut(new Date()); // Met à jour la date du dernier changement de statut
        ticketRepo.save(ticket);
    }*/

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkAndCloseTickets() {
        Date now = new Date();
        System.out.println("Checking for tickets to close...");

        // Vérifie les tickets dans les statuts spécifiés et ayant dépassé 5 secondes
        List<Ticket> tickets = ticketRepo.findAllByStatutTicketInAndDateDernierStatutBefore(
                Arrays.asList(StatutTicket.En_Attente, StatutTicket.Repondu, StatutTicket.Resolu),
                new Date(now.getTime() - (7 * 24 * 60 * 60 * 1000)) // 5 secondes pour tester
        );

        // Vérifie si la liste de tickets n'est pas vide
        if (!tickets.isEmpty()) {
            System.out.println("Tickets found: " + tickets.size());

            for (Ticket ticket : tickets) {
                // Met à jour le statut du ticket à "Cloturé"
                ticket.setStatutTicket(StatutTicket.Cloture);
                // Met à jour la date du dernier changement de statut
                ticket.setDateDernierStatut(new Date());
                // Sauvegarde les modifications dans la base de données
                ticketRepo.save(ticket);
                System.out.println("Ticket " + ticket.getIdTicket() + " closed.");
            }
        } else {
            System.out.println("No tickets to close.");
        }
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
