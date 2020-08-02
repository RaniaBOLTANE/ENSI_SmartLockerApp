package com.ensi.smartlocker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensi.smartlocker.domain.FicheRetour;
import com.ensi.smartlocker.repositories.FicheRetourRepository;

@Service
public class FicheRetourServiceImpl implements FicheRetourService {

	@Autowired
	private FicheRetourRepository fichRetourRepository;

	@Override
	public List<FicheRetour> getAllFicheRetours() {
		return fichRetourRepository.findAll();
	}

	@Override
	public FicheRetour getFicheRetourById(Integer id) {
		return fichRetourRepository.findById(id).orElse(null);
	}

	@Override
	public FicheRetour saveFicheRetour(FicheRetour ficheRetour) {
		return fichRetourRepository.save(ficheRetour);
	}

	@Override
	public FicheRetour getRealFicheRetourById(Integer id) {
		return fichRetourRepository.findById(id).get();
	}

	@Override
	public void deleteFicheRetourById(Integer id) {
		fichRetourRepository.deleteById(id);

	}

}
