package com.ensi.smartlocker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensi.smartlocker.domain.LigneFicheDemande;

public interface LigneFicheDemandeRepository extends JpaRepository<LigneFicheDemande, Integer> {

}
