package com.example.PORTClaimApp.DTO;

import com.example.PORTClaimApp.Entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketAttachementDTO {
    private Long idTicAttachDto;
    private String fileNameDto;
    private String fileTypeDto;
    private byte[] fileDataDto;
    private TicketDTO ticketDTO;
}
