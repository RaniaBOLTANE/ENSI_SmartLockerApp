package com.ensi.smartlocker.services;

import java.util.List;

import com.ensi.smartlocker.domain.LigneFicheEmprunt;

public interface LigneFicheEmpruntService {

	List<LigneFicheEmprunt> getAllLigneFicheEmprunt();

	LigneFicheEmprunt getLigneFicheEmpruntById(Integer id);

	LigneFicheEmprunt saveLigneFicheEmprunt(LigneFicheEmprunt ligneFicheEmprunt);

	LigneFicheEmprunt getRealLigneFicheEmpruntById(Integer id);

	void deleteLigneFicheEmpruntById(Integer id);

}
