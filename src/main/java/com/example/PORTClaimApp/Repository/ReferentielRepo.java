package com.example.PORTClaimApp.Repository;

import com.example.PORTClaimApp.Entity.Referentiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferentielRepo extends JpaRepository<Referentiel,Long> {
}
