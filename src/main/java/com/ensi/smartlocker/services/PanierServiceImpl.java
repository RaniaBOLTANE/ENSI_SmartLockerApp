package com.ensi.smartlocker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensi.smartlocker.domain.Panier;
import com.ensi.smartlocker.domain.User;
import com.ensi.smartlocker.repositories.PanierRepository;

@Service
public class PanierServiceImpl implements PanierService {

	@Autowired
	private PanierRepository panierRepository;

	public void setPanierRepository(PanierRepository panierRepository) {
		this.panierRepository = panierRepository;
	}

	@Override
	public Iterable<Panier> getAllPaniers() {
		return panierRepository.findAll();
	}

	@Override
	public Panier getPanierById(int id) {

		return panierRepository.findById(id).orElse(null);
	}

	@Override
	public Panier savePanier(Panier panier) {

		return panierRepository.save(panier);
	}

	@Override
	public void deletePanier(int id) {
		panierRepository.deleteById(id);

	}

	@Override
	public int getPanierByUser(String email) {
		return panierRepository.getByUserEmail(email);
	}

	@Override
	public Panier getRealPanierById(int id) {
		return panierRepository.findById(id).get();
	}

}
