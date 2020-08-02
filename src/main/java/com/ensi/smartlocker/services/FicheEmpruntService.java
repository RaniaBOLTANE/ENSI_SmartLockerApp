package com.ensi.smartlocker.services;

import java.util.List;

import com.ensi.smartlocker.domain.FicheEmprunt;

public interface FicheEmpruntService {

	List<FicheEmprunt> getAllFicheEmprunts();

	FicheEmprunt getFicheEmpruntById(Integer id);

	FicheEmprunt saveFicheEmprunt(FicheEmprunt ficheEmprunt);

	FicheEmprunt getRealFicheEmpruntById(Integer id);

	void deleteFicheEmpruntById(Integer id);

}
