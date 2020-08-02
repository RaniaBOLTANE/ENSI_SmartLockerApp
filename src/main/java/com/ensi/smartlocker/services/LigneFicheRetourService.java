package com.ensi.smartlocker.services;

import java.util.List;

import com.ensi.smartlocker.domain.LigneFicheEmprunt;
import com.ensi.smartlocker.domain.LigneFicheRetour;

public interface LigneFicheRetourService {

	List<LigneFicheRetour> getAllLigneFicheRetour();

	LigneFicheRetour getLigneFicheRetourById(Integer id);

	LigneFicheRetour saveLigneFicheRetour(LigneFicheRetour ligneFicheRetour);

	LigneFicheRetour getRealLigneFicheRetourById(Integer id);

	void deleteLigneFicheRetourById(Integer id);

}
