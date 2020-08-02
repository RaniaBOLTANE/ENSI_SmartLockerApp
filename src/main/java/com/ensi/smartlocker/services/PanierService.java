package com.ensi.smartlocker.services;

import com.ensi.smartlocker.domain.Panier;

public interface PanierService {

	Iterable<Panier> getAllPaniers();

	Panier getPanierById(int id);

	int getPanierByUser(String email);

	Panier savePanier(Panier categorie);

	void deletePanier(int id);
	
	Panier getRealPanierById(int id);

}
