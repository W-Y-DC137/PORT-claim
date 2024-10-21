package com.example.PORTClaimApp.Service;

import com.example.PORTClaimApp.DTO.TicketDTO;
import com.example.PORTClaimApp.Entity.Ticket;
import com.example.PORTClaimApp.Enums.StatutTicket;

import java.util.List;

public interface TicketService {
    TicketDTO createTicket(TicketDTO ticketDTO);
    TicketDTO getTicket(Long ticketId);
    List<TicketDTO> getTicketsByClientId(Long clientId);
    List<TicketDTO> getAllTickets();
    /*List<TicketDTO> getTicketByClient(Long clientId);
    List<TicketDTO> getTicketByAgent(Long agentId);*/
    TicketDTO updateTicket(Long ticketId,TicketDTO updatedTicketDto);
    /*void updateTicketStatus(Ticket ticket, StatutTicket nouveauStatut);*/
    void deleteTicket(Long ticketId);

}
