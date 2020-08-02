package com.ensi.smartlocker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensi.smartlocker.domain.LigneFicheEmprunt;
import com.ensi.smartlocker.repositories.LigneFicheEmpruntRepository;

@Service
public class LigneFicheEmpruntServiceImpl implements LigneFicheEmpruntService {

	@Autowired
	private LigneFicheEmpruntRepository ligneFicheEmpruntRepository;

	public void setLigneFicheEmpruntRepository(LigneFicheEmpruntRepository ligneFicheEmpruntRepository) {
		this.ligneFicheEmpruntRepository = ligneFicheEmpruntRepository;
	}

	@Override
	public List<LigneFicheEmprunt> getAllLigneFicheEmprunt() {
		return ligneFicheEmpruntRepository.findAll();
	}

	@Override
	public LigneFicheEmprunt getLigneFicheEmpruntById(Integer id) {
		return ligneFicheEmpruntRepository.findById(id).orElse(null);
	}

	@Override
	public LigneFicheEmprunt saveLigneFicheEmprunt(LigneFicheEmprunt ligneFicheEmprunt) {
		return ligneFicheEmpruntRepository.save(ligneFicheEmprunt);
	}

	@Override
	public LigneFicheEmprunt getRealLigneFicheEmpruntById(Integer id) {
		return ligneFicheEmpruntRepository.findById(id).get();
	}

	@Override
	public void deleteLigneFicheEmpruntById(Integer id) {
		ligneFicheEmpruntRepository.deleteById(id);

	}

}
