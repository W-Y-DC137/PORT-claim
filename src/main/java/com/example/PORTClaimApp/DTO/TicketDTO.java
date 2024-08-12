package com.example.PORTClaimApp.DTO;

import com.example.PORTClaimApp.Enums.NiveauUrgence;
import com.example.PORTClaimApp.Enums.StatutTicket;
import com.example.PORTClaimApp.Enums.TypeTicket;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    private Long idTicketDto;
    private TypeTicket typeTicketDto;
    private ThemeDTO themeDTO;
    private SousThemeDTO sousThemeDTO;
    private NiveauUrgence niveauUrgenceDto;
    private String objetDto;
    private String descriptionDto;
    private StatutTicket statusDto;
    private Date dateOuvertureDto;
    private Date dateResolutionDto;
    private Date dateFermetureDto;
    private Long idClientDto;
    private Long idAgentDto;
    private Long idAdminDTO;
}
