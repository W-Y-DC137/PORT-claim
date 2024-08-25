package com.example.PORTClaimApp.Service.ServiceImpl;

import com.example.PORTClaimApp.DTO.TicketAttachementDTO;
import com.example.PORTClaimApp.Entity.Ticket;
import com.example.PORTClaimApp.Entity.TicketAttachement;
import com.example.PORTClaimApp.Exception.RessourceNotFoundException;
import com.example.PORTClaimApp.Mapper.TicAttachMapper;
import com.example.PORTClaimApp.Mapper.TicketMapper;
import com.example.PORTClaimApp.Repository.TicketAttachementRepo;
import com.example.PORTClaimApp.Repository.TicketRepo;
import com.example.PORTClaimApp.Service.TicAttachService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TicAttachServiceImpl implements TicAttachService {
    @Autowired
    TicketAttachementRepo ticketAttachementRepo;
    @Autowired
    TicketMapper ticketMapper;
    @Autowired
    TicAttachMapper ticAttachMapper;

    @Autowired
    TicketRepo ticketRepo;
    @Override
    public TicketAttachementDTO createTicAttach(TicketAttachementDTO ticAttachDTO) {
        TicketAttachement ticketAttachement= ticAttachMapper.mapToTicAttach(ticAttachDTO);
        TicketAttachement savedTicketAttachement = ticketAttachementRepo.save(ticketAttachement);
        return TicAttachMapper.mapToTicAttachDTO(savedTicketAttachement);
    }

    @Override
    public TicketAttachementDTO getTicAttach(Long ticAttachId) {
        TicketAttachement ticketAttachement= ticketAttachementRepo.findById(ticAttachId)
                .orElseThrow(()->
                        new RessourceNotFoundException("Nous n'avons trouvé aucun attachement correspondant à l'ID fourni : " + ticAttachId + ". Veuillez vérifier l'ID et réessayer")
                        );

        return TicAttachMapper.mapToTicAttachDTO(ticketAttachement);
    }

    @Override
    public List<TicketAttachementDTO> getAllTicAttach() {
        List<TicketAttachement> attachements = ticketAttachementRepo.findAll();
        return attachements.stream().map((ticketAttachement)->TicAttachMapper.mapToTicAttachDTO(ticketAttachement))
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketAttachementDTO> getAttachByTic(Long ticketId) {
        Ticket ticket = ticketRepo.findById(ticketId).orElseThrow(()->
                new RessourceNotFoundException("aucun ticket est trouvé avec l'id "+ticketId)
                );

        List<TicketAttachement> ticketAttachements = ticketAttachementRepo.findByTicket(ticket);

        return ticketAttachements.stream().map((attachement)->TicAttachMapper.mapToTicAttachDTO(attachement))
                .collect(Collectors.toList());
    }

    @Override
    public TicketAttachementDTO updateTicAttach(Long ticAttachId, TicketAttachementDTO updatedTicAttachDto) {
        TicketAttachement ticketAttachement= ticketAttachementRepo.findById(ticAttachId)
                .orElseThrow(()->
                        new RessourceNotFoundException("Nous n'avons trouvé aucun attachement correspondant à l'ID fourni : " + ticAttachId + ". Veuillez vérifier l'ID et réessayer")
                );
        ticketAttachement.setFileName(updatedTicAttachDto.getFileNameDto());
        ticketAttachement.setFileType(updatedTicAttachDto.getFileTypeDto());
        ticketAttachement.setFileData(updatedTicAttachDto.getFileDataDto());
        ticketAttachement.setTicket(ticketRepo.findById(updatedTicAttachDto.getTicketId()).orElse(null));

        TicketAttachement updatedTicketAttachement = ticketAttachementRepo.save(ticketAttachement);
        return TicAttachMapper.mapToTicAttachDTO(updatedTicketAttachement);
    }

    @Override
    public void deleteTicAttach(Long ticAttachId) {
        TicketAttachement ticketAttachement= ticketAttachementRepo.findById(ticAttachId)
                .orElseThrow(()->
                        new RessourceNotFoundException("Nous n'avons trouvé aucun attachement correspondant à l'ID fourni : " + ticAttachId + ". Veuillez vérifier l'ID et réessayer")
                );
        ticketAttachementRepo.deleteById(ticAttachId);

    }

    @Override
    public TicketAttachement saveAttachement(MultipartFile file,Long idTicket) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if(fileName.contains("..")){
                throw  new Exception("Le nom de fichier contient une séquence de caractères invalide." +fileName);
            }

            Ticket ticket = ticketRepo.findById(idTicket).orElseThrow(()->
                    new Exception("Ticket non trouvé avec l'ID: " + idTicket));

            TicketAttachement attachement = new TicketAttachement(
                    null, // hada raytgenera automatiquement
                    fileName,
                    file.getContentType(),
                    file.getBytes(),
                    ticket //yarbi tkhdm had l3iba
            );

            return ticketAttachementRepo.save(attachement);

        }catch (Exception e) {
            // Handle exception (e.g., log the error)
            e.printStackTrace();
            // You may want to throw a custom exception here
            throw new RuntimeException("Failed to store file " + fileName, e);
        }
    }
}
