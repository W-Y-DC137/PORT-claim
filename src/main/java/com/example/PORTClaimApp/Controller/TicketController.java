package com.example.PORTClaimApp.Controller;

import com.example.PORTClaimApp.DTO.TicketDTO;
import com.example.PORTClaimApp.Service.TicAttachService;
import com.example.PORTClaimApp.Service.TicketService;
import lombok.AllArgsConstructor;
import lombok.Lombok;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/api/tickets")
public class TicketController {
    TicketService ticketService;

    //create Ticket REST API
    @PostMapping
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO ticketDto){
        TicketDTO savedTicket = ticketService.createTicket(ticketDto);
        return new ResponseEntity<>(savedTicket, HttpStatus.CREATED);
    }

    //get Ticket REST API
    @GetMapping("{id}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable("id") Long ticketId){
        TicketDTO ticketDto = ticketService.getTicket(ticketId);
        return ResponseEntity.ok(ticketDto);
    }

    //get all Tickets REST API
    @GetMapping
    public ResponseEntity<List <TicketDTO>> getAllTicket(){
        List<TicketDTO> ticketDtos = ticketService.getAllTickets();
        return ResponseEntity.ok(ticketDtos);
    }

    //update Ticket REST API
    @PutMapping("{id}")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable("id") Long ticketId ,@RequestBody TicketDTO updatedTicket){
        TicketDTO ticketDto = ticketService.updateTicket(ticketId,updatedTicket);
        return ResponseEntity.ok(ticketDto);
    }

    //delete Ticket REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String > deleteTicket(@PathVariable("id") Long ticketId){
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.ok("ticket supprimé avec succé");
    }
}
