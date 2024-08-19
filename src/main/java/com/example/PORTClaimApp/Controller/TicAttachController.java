package com.example.PORTClaimApp.Controller;

import com.example.PORTClaimApp.Attachements.TicAttResponseData;
import com.example.PORTClaimApp.DTO.TicketAttachementDTO;
import com.example.PORTClaimApp.Entity.TicketAttachement;
import com.example.PORTClaimApp.Service.TicAttachService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/api/ticketAttachements")
public class TicAttachController {

    TicAttachService ticAttachService;

    public TicAttachController(TicAttachService ticAttachService) {
        this.ticAttachService = ticAttachService;
    }

    //UPLOAD REST API
    @PostMapping("/upload/{id}")
    public TicAttResponseData uploadFile(@PathVariable("id") Long ticketId, @RequestParam("file") MultipartFile file) {
        // Validate file type and size
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        // Additional validations can be added here

        TicketAttachement ticketAttachement = ticAttachService.saveAttachement(file, ticketId);
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/ticketAttachements/download/")
                .path(ticketAttachement.getIdTicketAttacnement().toString())
                .toUriString();

        return new TicAttResponseData(ticketAttachement.getFileName(),
                downloadURL,
                file.getContentType(),
                file.getSize());
    }


    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileId") Long fileId) {
        TicketAttachementDTO attachement = ticAttachService.getTicAttach(fileId);
        if (attachement == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachement.getFileTypeDto()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachement.getFileNameDto() + "\"")
                .body(new ByteArrayResource(attachement.getFileDataDto()));
    }




    //Create TicketAttach Rest API.
    @PostMapping
    public ResponseEntity<TicketAttachementDTO> createTickAttach(@RequestBody TicketAttachementDTO ticketAttachementDto){
        TicketAttachementDTO savedTicketAttachement = ticAttachService.createTicAttach(ticketAttachementDto);
        return new ResponseEntity<>(savedTicketAttachement, HttpStatus.CREATED);
    }

    // GetTickAttach By id Rest API.

    @GetMapping("{id}")
    public ResponseEntity<TicketAttachementDTO> getTickAttach(@PathVariable("id") Long id ){
        TicketAttachementDTO ticketAttachementDTO = ticAttachService.getTicAttach(id);
        return ResponseEntity.ok(ticketAttachementDTO);
    }

    //GetAllTickAttach REST APIs.

    @GetMapping
    public ResponseEntity<List<TicketAttachementDTO>> getAllTickAttach(){
        List<TicketAttachementDTO> ticketAttachementDTOS = ticAttachService.getAllTicAttach();
        return ResponseEntity.ok(ticketAttachementDTOS);
    }

    //Update TicAttach REST APIs.

    @PutMapping("{id}")
    public ResponseEntity<TicketAttachementDTO> updateTicAttach(@PathVariable("id") Long id , @RequestBody TicketAttachementDTO ticketAttachementDto){
        TicketAttachementDTO ticketAttachement = ticAttachService.updateTicAttach(id,ticketAttachementDto);
        return ResponseEntity.ok(ticketAttachement);
    }

    //Delete TicAttach REST APIs
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTicAttach(@PathVariable("id") Long id){
        ticAttachService.deleteTicAttach(id);
        return ResponseEntity.ok("Attachement supprimé avec succé");
    }

}
