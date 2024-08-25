package com.example.PORTClaimApp.Service.ServiceImpl;

import com.example.PORTClaimApp.DTO.ReferentielDTO;
import com.example.PORTClaimApp.Entity.Referentiel;
import com.example.PORTClaimApp.Exception.RessourceNotFoundException;
import com.example.PORTClaimApp.Mapper.ReferentielMapper;
import com.example.PORTClaimApp.Repository.ReferentielRepo;
import com.example.PORTClaimApp.Service.ReferentielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Ref;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReferencielServiceImpl implements ReferentielService {
    @Autowired
    ReferentielRepo referentielRepo;

    @Override
    public ReferentielDTO createReferentiel(ReferentielDTO referentielDTO) {
        Referentiel referentiel = ReferentielMapper.INSTANCE.toReferentiel(referentielDTO);
        return ReferentielMapper.INSTANCE.toReferentielDTO(referentielRepo.save(referentiel));
    }

    @Override
    public ReferentielDTO getReferentiel(Long id) {
        Referentiel referentiel = referentielRepo.findById(id).orElseThrow(()->
                new RessourceNotFoundException("referentiel non trouvé avec l'id :"+id)
                );
        return ReferentielMapper.INSTANCE.toReferentielDTO(referentiel);
    }

    @Override
    public List<ReferentielDTO> getAll() {
        List<Referentiel> referentiels = referentielRepo.findAll();


        return referentiels.stream().map((referentiel)->ReferentielMapper.INSTANCE.toReferentielDTO(referentiel))
                .collect(Collectors.toList());
    }

    @Override
    public ReferentielDTO updateReferentiel(Long id, ReferentielDTO referentielDto) {
        Referentiel referentiel = referentielRepo.findById(id).orElseThrow(()->
                new RessourceNotFoundException("referentiel non trouvé avec l'id:"+id)
                );

        referentiel.setId(referentielDto.getId());
        referentiel.setCode(referentielDto.getCode());
        referentiel.setValeur(referentielDto.getValeur());
        referentiel.setLibelle(referentielDto.getLibelle());
        referentiel.setParent(ReferentielMapper.INSTANCE.toReferentiel(referentielDto.getParent()));
        return ReferentielMapper.INSTANCE.toReferentielDTO(referentiel);
    }

    @Override
    public void deleteById(Long id) {
        Referentiel referentiel = referentielRepo.findById(id).orElseThrow(()->
        new RessourceNotFoundException("referentiel non trouvé avec l'id :"+id)
        );
        referentielRepo.deleteById(id);
    }
}
