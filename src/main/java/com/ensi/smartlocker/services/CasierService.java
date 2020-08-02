package com.ensi.smartlocker.services;

import com.ensi.smartlocker.domain.Casier;

public interface CasierService {

	Iterable<Casier> getAllCasiers();

	Casier getCasierById(int idCasier);

	Casier saveCasier(Casier Casier);

	void deleteCasierById(int idCasier);

	Casier getRealCasierById(int idCasier);
	
	Casier getRealCasierByRef(int idCasier);

}
