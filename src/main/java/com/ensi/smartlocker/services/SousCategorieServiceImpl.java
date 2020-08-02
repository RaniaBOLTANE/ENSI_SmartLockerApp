package com.ensi.smartlocker.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensi.smartlocker.domain.Categorie;
import com.ensi.smartlocker.domain.SousCategorie;
import com.ensi.smartlocker.repositories.SousCategorieRepository;

@Service
public class SousCategorieServiceImpl implements SousCategorieService {

	private SousCategorieRepository sousCategorieRepository;

	@Autowired
	public void setSousCategorieRepository(SousCategorieRepository sousCategorieRepository) {
		this.sousCategorieRepository = sousCategorieRepository;
	}

	@Override
	public Iterable<SousCategorie> getAllSousCategories() {
		return sousCategorieRepository.findAll();
	}

	@Override
	public SousCategorie getSousCategorieById(String sousCategorie) {
		return sousCategorieRepository.findById(sousCategorie).orElse(null);
	}

	@Override
	public SousCategorie saveSousCategorie(SousCategorie sousCategorie) {
		return sousCategorieRepository.save(sousCategorie);
	}

	@Override
	public Set<SousCategorie> getSousCategoriesByCategorie(Categorie categorie) {
		return categorie.getSousCategories();
	}

	@Override
	public SousCategorie getSousCategorieByMaterielId(Integer referenceMateriel) {
		return sousCategorieRepository.getByMaterielId(referenceMateriel);
	}

	@Override
	public void updateSouscategorieMteriel(String souscategorie, Integer ref) {
		sousCategorieRepository.updateSouscategorieMteriel(souscategorie, ref);
		
	}

}
