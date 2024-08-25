package com.example.PORTClaimApp.Repository;

import com.example.PORTClaimApp.Entity.Ticket;
import com.example.PORTClaimApp.Entity.TicketAttachement;
import com.example.PORTClaimApp.Entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketAttachementRepo extends JpaRepository<TicketAttachement,Long> {
 List<TicketAttachement> findByTicket(Ticket ticket);
}
