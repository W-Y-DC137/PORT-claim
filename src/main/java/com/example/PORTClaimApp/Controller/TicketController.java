package com.example.PORTClaimApp.Controller;

import com.example.PORTClaimApp.DTO.TicketDTO;
import com.example.PORTClaimApp.Entity.Ticket;
import com.example.PORTClaimApp.Entity.Utilisateur;
import com.example.PORTClaimApp.Enums.StatutTicket;
import com.example.PORTClaimApp.Repository.TicketRepo;
import com.example.PORTClaimApp.Repository.UtilisateurRepo;
import com.example.PORTClaimApp.Service.ServiceImpl.EmailService;
import com.example.PORTClaimApp.Service.TicAttachService;
import com.example.PORTClaimApp.Service.TicketService;
import com.example.PORTClaimApp.Service.UtilisateurService;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.Lombok;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;
    private final TicketRepo ticketRepo;
    private final UtilisateurRepo utilisateurRepo;
    private final EmailService emailService;

    // Inject dependencies via constructor
    public TicketController(TicketService ticketService, TicketRepo ticketRepo, UtilisateurRepo utilisateurRepo, EmailService emailService) {
        this.ticketService = ticketService;
        this.ticketRepo = ticketRepo;
        this.utilisateurRepo = utilisateurRepo;
        this.emailService = emailService;
    }

    // In TicketController

    @PostMapping("/{id}/notify-client")
    public ResponseEntity<String> notifyClient(@PathVariable("id") Long ticketId, @RequestBody Map<String, Long> payload) throws MessagingException, UnsupportedEncodingException {
        Long clientId = payload.get("clientId");

        // Récupérer les détails du client
        Utilisateur client = utilisateurRepo.findById(clientId).orElse(null);
        if (client == null || client.getEmail() == null) {
            return new ResponseEntity<>("Client introuvable ou e-mail manquant", HttpStatus.NOT_FOUND);
        }

        // Récupérer les détails du ticket
        Ticket ticket = ticketRepo.findById(ticketId).orElseThrow(
                () -> new RuntimeException("Aucun ticket trouvé avec l'id " + ticketId)
        );

        // Préparer le contenu de l'email en HTML
        String subject = "Confirmation de la résolution du ticket";
        String ticketUrl = "http://localhost:3000/login?redirect=/client/tickets/" + ticketId;
        String content = "<p>Bonjour " + client.getNomUtilisateur() + ",</p>"
                + "<p>Nous avons traité votre ticket numéro " + ticketId + " concernant :</p>"
                + "<p><strong>Objet:</strong> " + ticket.getObjet() + "</p>"
                + "<p><strong>Description:</strong> " + ticket.getDescription() + "</p>"
                + "<p>Merci de cliquer sur le lien suivant pour confirmer la résolution de votre ticket :</p>"
                + "<p><a href=\"" + ticketUrl + "\">Confirmer la résolution du ticket</a></p>"
                + "<br>"
                + "<p>Cordialement,</p>"
                + "<p>L'équipe de support</p>";

        // Envoyer l'email
        String fromAddress = "no-reply@portnet.ma";
        String fromName = "Portnet HelpDesk";
        emailService.sendEmailWithSender(client.getEmail(), subject, content, fromAddress, fromName);

        return ResponseEntity.ok("Email envoyé avec succès à " + client.getEmail());
    }


    @PostMapping("/{id}/notify-client-message")
    public ResponseEntity<String> notifyClientMessage(@PathVariable("id") Long ticketId, @RequestBody Map<String, Long> payload) {
        Long clientId = payload.get("clientId");

        // Récupérer les détails du client
        Utilisateur client = utilisateurRepo.findById(clientId).orElse(null);
        if (client == null || client.getEmail() == null) {
            return new ResponseEntity<>("Client introuvable ou e-mail manquant", HttpStatus.NOT_FOUND);
        }

        // Récupérer les détails du ticket
        Ticket ticket = ticketRepo.findById(ticketId).orElseThrow(
                () -> new RuntimeException("Aucun ticket trouvé avec l'id " + ticketId)
        );

        // Préparer le contenu de l'email avec un lien vers le ticket
        String subject = "Nouveau message pour le ticket " + ticketId;
        String ticketUrl = "http://localhost:3000/login?redirect=/client/tickets/" + ticketId;
        String content = "<p>Bonjour " + client.getNomUtilisateur() + ",</p>"
                + "<p>Vous avez reçu un nouveau message concernant votre ticket numéro " + ticketId + ".</p>"
                + "<p>Objet : " + ticket.getObjet() + "</p>"
                + "<p>Description : " + ticket.getDescription() + "</p>"
                + "<p><a href=\"" + ticketUrl + "\">Cliquez ici pour consulter le message</a></p>"
                + "<br>"
                + "<p>Cordialement,</p>"
                + "<p>L'équipe de support</p>";

        // Envoyer l'email
        String fromAddress = "no-reply@portnet.ma";
        String fromName = "Portnet HelpDesk";
        emailService.sendEmailWithSender(client.getEmail(), subject, content, fromAddress, fromName);

        return ResponseEntity.ok("Notification de message envoyée avec succès à " + client.getEmail());
    }




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

    //get Ticket  BY Client REST API

    @GetMapping("client/{clientId}")
    public ResponseEntity<List<TicketDTO>> getTicketsByClientId(@PathVariable("clientId") Long clientId){
      List<TicketDTO> ticketDtos  = ticketService.getTicketsByClientId(clientId);
      return ResponseEntity.ok(ticketDtos);
    }

    //get all Tickets REST API
    @GetMapping
    public ResponseEntity<List <TicketDTO>> getAllTicket(){
        List<TicketDTO> ticketDtos = ticketService.getAllTickets();
        return ResponseEntity.ok(ticketDtos);
    }

    //update Ticket REST API
    @PutMapping("{id}")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable("id") Long ticketId ,@RequestBody TicketDTO updateTic){
        TicketDTO ticketDto = ticketService.updateTicket(ticketId,updateTic);
        return ResponseEntity.ok(ticketDto);
    }

    //update ticket status after 7 days to cloturé
   /* @PutMapping("/{id}/update-status")
    public ResponseEntity<String> updateTicketStatus(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        String nouveauStatutStr = payload.get("statutTicket");
        StatutTicket nouveauStatut = StatutTicket.valueOf(nouveauStatutStr);

        Ticket ticket = ticketRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id " + id));

        ticketService.updateTicketStatus(ticket, nouveauStatut);  // Appel de la méthode dans le service

        return ResponseEntity.ok("Statut du ticket mis à jour");
    }
*/
    //delete Ticket REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String > deleteTicket(@PathVariable("id") Long ticketId){
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.ok("ticket supprimé avec succé");
    }
}
