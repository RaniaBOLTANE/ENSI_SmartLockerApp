package com.ensi.smartlocker.services;

import java.util.List;

import com.ensi.smartlocker.domain.LigneFicheDemande;

public interface LigneFicheDemandeService {

	List<LigneFicheDemande> getAllLigneFicheDemande();

	LigneFicheDemande getLigneFicheDemandeById(Integer id);

	LigneFicheDemande saveLigneFicheDemande(LigneFicheDemande ligneFicheDemande);

	LigneFicheDemande getRealLigneFicheDemandeById(Integer id);

	void deleteLigneFicheDemandeById(Integer id);

}
