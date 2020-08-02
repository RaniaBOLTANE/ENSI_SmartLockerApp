package com.ensi.smartlocker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensi.smartlocker.domain.FicheDemande;

public interface FicheDemandeRepository extends JpaRepository<FicheDemande, Integer> {

}
