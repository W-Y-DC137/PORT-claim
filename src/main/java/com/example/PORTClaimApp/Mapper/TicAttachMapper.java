package com.example.PORTClaimApp.Mapper;

import com.example.PORTClaimApp.DTO.TicketAttachementDTO;
import com.example.PORTClaimApp.Entity.Ticket;
import com.example.PORTClaimApp.Entity.TicketAttachement;
import com.example.PORTClaimApp.Repository.TicketAttachementRepo;
import com.example.PORTClaimApp.Repository.TicketRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class TicAttachMapper {
    TicketMapper ticketMapper;

    TicketRepo ticketRepo;
    public static TicketAttachementDTO mapToTicAttachDTO (TicketAttachement ticketAttachement){
        return new TicketAttachementDTO(
                ticketAttachement.getIdTicketAttacnement(),
                ticketAttachement.getFileName(),
                ticketAttachement.getFileType(),
                ticketAttachement.getFileData(),
                ticketAttachement.getTicket().getIdTicket()
        );
    }

    public  TicketAttachement mapToTicAttach (TicketAttachementDTO ticketAttachementDTO){
         TicketAttachement ticketAttachement = new TicketAttachement();
                ticketAttachement.setFileName(ticketAttachementDTO.getFileNameDto());
                ticketAttachement.setFileType(ticketAttachementDTO.getFileTypeDto());
                ticketAttachement.setFileData(ticketAttachementDTO.getFileDataDto());
                if(ticketAttachementDTO.getTicketId()!=null){
                    ticketAttachement.setTicket(ticketRepo.findById(ticketAttachementDTO.getTicketId()).orElse(null));
                }
        return ticketAttachement;
    }
}
