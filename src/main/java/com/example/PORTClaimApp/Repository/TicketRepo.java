package com.example.PORTClaimApp.Repository;

import com.example.PORTClaimApp.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<Ticket,Long> {
}
