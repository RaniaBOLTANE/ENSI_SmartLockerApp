package com.ensi.smartlocker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensi.smartlocker.domain.LigneFicheEmprunt;
import com.ensi.smartlocker.domain.LigneFicheRetour;
import com.ensi.smartlocker.repositories.LigneFicheEmpruntRepository;
import com.ensi.smartlocker.repositories.LigneFicheRetourRepository;

@Service
public class LigneFicheRetourServiceImpl implements LigneFicheRetourService {

	@Autowired
	private LigneFicheRetourRepository ligneFicheRetourRepository;


	public void setLigneFicheRetourRepository(LigneFicheRetourRepository ligneFicheRetourRepository) {
		this.ligneFicheRetourRepository = ligneFicheRetourRepository;
	}

	@Override
	public List<LigneFicheRetour> getAllLigneFicheRetour() {
		return ligneFicheRetourRepository.findAll();
	}

	@Override
	public LigneFicheRetour getLigneFicheRetourById(Integer id) {
		return ligneFicheRetourRepository.findById(id).orElse(null);
	}

	@Override
	public LigneFicheRetour saveLigneFicheRetour(LigneFicheRetour ligneFicheRetour) {
		return ligneFicheRetourRepository.save(ligneFicheRetour);
	}

	@Override
	public LigneFicheRetour getRealLigneFicheRetourById(Integer id) {
		return ligneFicheRetourRepository.findById(id).get();
	}

	@Override
	public void deleteLigneFicheRetourById(Integer id) {
		ligneFicheRetourRepository.deleteById(id);;
		
	}



}
