package com.ensi.smartlocker.services;

import java.util.List;
import java.util.Set;

import com.ensi.smartlocker.domain.LignePanier;
import com.ensi.smartlocker.domain.Materiel;

public interface LignePanierService {

	List<LignePanier> getAllLignePanier();

	LignePanier getLignePanierById(Integer id);

	LignePanier saveLignePanier(LignePanier lignePanier);

	int getRealLignePanierById(Integer materielId, Integer panierId);

	void deleteLignePanierById(Integer id);

	Set<LignePanier> getLPByMaterial(Materiel m);

}
