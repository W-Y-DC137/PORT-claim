package com.example.PORTClaimApp.Service;

import com.example.PORTClaimApp.DTO.TicketAttachementDTO;

import java.util.List;

public interface TicAttachService {
    TicketAttachementDTO createTicAttach(TicketAttachementDTO ticAttachDTO);
    TicketAttachementDTO getTicAttach(Long ticAttachId);
    List<TicketAttachementDTO> getAllTicAttach();
    TicketAttachementDTO updateTicAttach(Long ticAttachId,TicketAttachementDTO updatedTicAttachDto);
    void deleteTicAttach(Long ticAttachId);
}
