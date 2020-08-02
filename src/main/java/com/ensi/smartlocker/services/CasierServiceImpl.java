package com.ensi.smartlocker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensi.smartlocker.domain.Casier;
import com.ensi.smartlocker.repositories.CasierRepository;

@Service
public class CasierServiceImpl implements CasierService {

	
	private CasierRepository CasierRepository;

	@Autowired
	public void setCasierRepository(CasierRepository CasierRepository) {
		this.CasierRepository = CasierRepository;
	}

	@Override
	public Iterable<Casier> getAllCasiers() {
		return CasierRepository.findAll();
	}

	@Override
	public Casier getCasierById(int idCasier) {
		return CasierRepository.findById(idCasier).orElse(null);
	}

	@Override
	public Casier saveCasier(Casier Casier) {
		return CasierRepository.save(Casier);
	}

	@Override
	public void deleteCasierById(int idCasier) {
		CasierRepository.deleteById(idCasier);

	}

	@Override
	public Casier getRealCasierById(int idCasier) {
		return CasierRepository.findById(idCasier).get();
	}

	@Override
	public Casier getRealCasierByRef(int referenceMateriel) {
		return CasierRepository.getByreference(referenceMateriel);
	}

}
