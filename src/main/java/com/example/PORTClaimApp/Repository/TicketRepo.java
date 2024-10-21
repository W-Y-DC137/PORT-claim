package com.example.PORTClaimApp.Repository;

import com.example.PORTClaimApp.Entity.Ticket;
import com.example.PORTClaimApp.Entity.Utilisateur;
import com.example.PORTClaimApp.Enums.StatutTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TicketRepo extends JpaRepository<Ticket,Long> {
    List<Ticket> findByClient(Utilisateur client);

    List<Ticket> findAllByStatutTicketInAndDateDernierStatutBefore(List<StatutTicket> statuts, Date date);
}
