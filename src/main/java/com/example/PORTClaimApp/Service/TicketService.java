package com.example.PORTClaimApp.Service;

import com.example.PORTClaimApp.DTO.TicketDTO;

import java.util.List;

public interface TicketService {
    TicketDTO createTicket(TicketDTO ticketDTO);
    TicketDTO getTicket(Long ticketId);
    List<TicketDTO> getTicketsByClientId(Long clientId);
    List<TicketDTO> getAllTickets();
    /*List<TicketDTO> getTicketByClient(Long clientId);
    List<TicketDTO> getTicketByAgent(Long agentId);*/
    TicketDTO updateTicket(Long ticketId,TicketDTO updatedTicketDto);
    void deleteTicket(Long ticketId);

}
