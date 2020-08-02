package com.ensi.smartlocker.services;

import com.ensi.smartlocker.domain.Categorie;

public interface CategorieService {

	Iterable<Categorie> getAllCategories();

	Categorie getCategorieById(String categorie);
	
	Categorie getCategorieBySousCategorie(String souscategorie);

	Categorie saveCategorie(Categorie categorie);

}
