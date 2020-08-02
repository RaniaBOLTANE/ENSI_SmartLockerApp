package com.ensi.smartlocker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensi.smartlocker.domain.LigneFicheDemande;
import com.ensi.smartlocker.repositories.LigneFicheDemandeRepository;

@Service
public class LigneFicheDemandeServiceImpl implements LigneFicheDemandeService {

	@Autowired
	private LigneFicheDemandeRepository ligneFicheDemandeRepository;

	@Override
	public List<LigneFicheDemande> getAllLigneFicheDemande() {
		return ligneFicheDemandeRepository.findAll();
	}

	@Override
	public LigneFicheDemande getLigneFicheDemandeById(Integer id) {
		return ligneFicheDemandeRepository.findById(id).orElse(null);
	}

	@Override
	public LigneFicheDemande saveLigneFicheDemande(LigneFicheDemande ligneFicheDemande) {
		return ligneFicheDemandeRepository.save(ligneFicheDemande);
	}

	@Override
	public LigneFicheDemande getRealLigneFicheDemandeById(Integer id) {
		return ligneFicheDemandeRepository.findById(id).get();
	}

	@Override
	public void deleteLigneFicheDemandeById(Integer id) {
		ligneFicheDemandeRepository.deleteById(id);

	}

}
