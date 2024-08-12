package com.example.PORTClaimApp.Repository;

import com.example.PORTClaimApp.Entity.TicketAttachement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketAttachementRepo extends JpaRepository<TicketAttachement,Long> {
}
