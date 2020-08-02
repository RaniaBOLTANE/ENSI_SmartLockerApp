package com.ensi.smartlocker.services;

import java.util.Set;

import com.ensi.smartlocker.domain.Categorie;
import com.ensi.smartlocker.domain.SousCategorie;

public interface SousCategorieService {

	Iterable<SousCategorie> getAllSousCategories();

	SousCategorie getSousCategorieById(String sousCategorie);

	SousCategorie getSousCategorieByMaterielId(Integer referenceMateriel);

	SousCategorie saveSousCategorie(SousCategorie sousCategorie);

	Set<SousCategorie> getSousCategoriesByCategorie(Categorie categorie);

	void updateSouscategorieMteriel(String souscategorie, Integer ref);

}
