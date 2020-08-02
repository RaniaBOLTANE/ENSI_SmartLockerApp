package com.ensi.smartlocker.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensi.smartlocker.domain.FicheEmprunt;
import com.ensi.smartlocker.domain.Materiel;
import com.ensi.smartlocker.repositories.FicheEmpruntRepository;

@Service
public class FicheEmpruntServiceImpl implements FicheEmpruntService {

	@Autowired
	private FicheEmpruntRepository ficheEmpruntRepository;

	public void setFicheEmpruntRepository(FicheEmpruntRepository ficheEmpruntRepository) {
		this.ficheEmpruntRepository = ficheEmpruntRepository;
	}

	@Override
	public List<FicheEmprunt> getAllFicheEmprunts() {
		return ficheEmpruntRepository.findAll();
	}

	@Override
	public FicheEmprunt getFicheEmpruntById(Integer id) {
		return ficheEmpruntRepository.findById(id).orElse(null);
	}

	@Override
	public FicheEmprunt saveFicheEmprunt(FicheEmprunt ficheEmprunt) {
		return ficheEmpruntRepository.save(ficheEmprunt);
	}

	@Override
	public FicheEmprunt getRealFicheEmpruntById(Integer id) {
		return ficheEmpruntRepository.findById(id).get();
	}

	@Override
	public void deleteFicheEmpruntById(Integer id) {
		ficheEmpruntRepository.deleteById(id);
		
	}
	
	

}
