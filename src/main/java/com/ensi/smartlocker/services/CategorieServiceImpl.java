package com.ensi.smartlocker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensi.smartlocker.domain.Categorie;
import com.ensi.smartlocker.repositories.CategorieRepository;

@Service
public class CategorieServiceImpl implements CategorieService {

	private CategorieRepository categorieRepository;

	@Autowired
	public void setCategorieRepository(CategorieRepository categorieRepository) {
		this.categorieRepository = categorieRepository;
	}

	@Override
	public Iterable<Categorie> getAllCategories() {
		return categorieRepository.findAll();
	}

	@Override
	public Categorie getCategorieById(String categorie) {

		return categorieRepository.findById(categorie).orElse(null);
	}

	@Override
	public Categorie saveCategorie(Categorie categorie) {
		return categorieRepository.save(categorie);
	}

	@Override
	public Categorie getCategorieBySousCategorie(String souscategorie) {
		return categorieRepository.getBySousCategorieId(souscategorie);
	}

}
