package com.example.PORTClaimApp.Service;

import com.example.PORTClaimApp.DTO.TicketAttachementDTO;
import com.example.PORTClaimApp.Entity.Ticket;
import com.example.PORTClaimApp.Entity.TicketAttachement;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TicAttachService {
    TicketAttachementDTO createTicAttach(TicketAttachementDTO ticAttachDTO);
    TicketAttachementDTO getTicAttach(Long ticAttachId);
    List<TicketAttachementDTO> getAllTicAttach();
    List<TicketAttachementDTO> getAttachByTic(Long ticketId);
    TicketAttachementDTO updateTicAttach(Long ticAttachId,TicketAttachementDTO updatedTicAttachDto);
    void deleteTicAttach(Long ticAttachId);

    TicketAttachement saveAttachement(MultipartFile file, Long ticketId);
}
