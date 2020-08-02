package com.ensi.smartlocker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensi.smartlocker.domain.FicheDemande;
import com.ensi.smartlocker.repositories.FicheDemandeRepository;

@Service
public class FicheDemandeServiceImpl implements FicheDemandeService {

	@Autowired
	private FicheDemandeRepository ficheDemandeRepository;

	public void setFicheEmpruntRepository(FicheDemandeRepository ficheDemandeRepository) {
		this.ficheDemandeRepository = ficheDemandeRepository;
	}

	@Override
	public List<FicheDemande> getAllFicheDemandes() {
		return ficheDemandeRepository.findAll();
	}

	@Override
	public FicheDemande getFicheDemandeById(Integer id) {
		return ficheDemandeRepository.findById(id).orElse(null);
	}

	@Override
	public FicheDemande saveFicheDemande(FicheDemande ficheDemande) {
		return ficheDemandeRepository.save(ficheDemande);
	}

	@Override
	public FicheDemande getRealFicheDemandeById(Integer id) {
		return ficheDemandeRepository.findById(id).get();
	}

	@Override
	public void deleteFicheDemandeById(Integer id) {
		ficheDemandeRepository.deleteById(id);

	}

}
