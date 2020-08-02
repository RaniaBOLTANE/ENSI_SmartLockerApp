package com.ensi.smartlocker.services;

import java.util.List;

import com.ensi.smartlocker.domain.FicheEmprunt;
import com.ensi.smartlocker.domain.FicheRetour;

public interface FicheRetourService {

	List<FicheRetour> getAllFicheRetours();

	FicheRetour getFicheRetourById(Integer id);

	FicheRetour saveFicheRetour(FicheRetour ficheRetour);

	FicheRetour getRealFicheRetourById(Integer id);

	void deleteFicheRetourById(Integer id);

}
