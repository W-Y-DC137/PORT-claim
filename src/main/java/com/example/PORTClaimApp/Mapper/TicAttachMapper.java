package com.example.PORTClaimApp.Mapper;

import com.example.PORTClaimApp.DTO.TicketAttachementDTO;
import com.example.PORTClaimApp.Entity.TicketAttachement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class TicAttachMapper {
    TicketMapper ticketMapper;
    public static TicketAttachementDTO mapToTicAttachDTO (TicketAttachement ticketAttachement){
        return new TicketAttachementDTO(
                ticketAttachement.getIdTicketAttacnement(),
                ticketAttachement.getFileName(),
                ticketAttachement.getFileType(),
                ticketAttachement.getFileData(),
                TicketMapper.mapToTicketDTO(ticketAttachement.getTicket())
        );
    }

    public  TicketAttachement mapToTicAttach (TicketAttachementDTO ticketAttachementDTO){
        return new TicketAttachement(
                ticketAttachementDTO.getIdTicAttachDto(),
                ticketAttachementDTO.getFileNameDto(),
                ticketAttachementDTO.getFileTypeDto(),
                ticketAttachementDTO.getFileDataDto(),
                ticketMapper.mapToTicket(ticketAttachementDTO.getTicketDTO())
        );
    }
}
