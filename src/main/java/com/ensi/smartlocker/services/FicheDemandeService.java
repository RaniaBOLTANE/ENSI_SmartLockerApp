package com.ensi.smartlocker.services;

import java.util.List;

import com.ensi.smartlocker.domain.FicheDemande;


public interface FicheDemandeService {

	List<FicheDemande> getAllFicheDemandes();

	FicheDemande getFicheDemandeById(Integer id);

	FicheDemande saveFicheDemande(FicheDemande ficheEmprunt);

	FicheDemande getRealFicheDemandeById(Integer id);

	void deleteFicheDemandeById(Integer id);

}
