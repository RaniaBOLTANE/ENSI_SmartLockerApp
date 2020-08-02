package com.ensi.smartlocker.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensi.smartlocker.domain.LignePanier;
import com.ensi.smartlocker.domain.Materiel;
import com.ensi.smartlocker.repositories.LignePanierRepository;

@Service
public class LignePanierServiceImpl implements LignePanierService {

	@Autowired
	private LignePanierRepository lignePanierRepository;

	@Override
	public List<LignePanier> getAllLignePanier() {

		return lignePanierRepository.findAll();
	}

	@Override
	public LignePanier getLignePanierById(Integer id) {
		return lignePanierRepository.findById(id).orElse(null);
	}

	@Override
	public LignePanier saveLignePanier(LignePanier lignePanier) {
		return lignePanierRepository.save(lignePanier);
	}

	@Override
	public void deleteLignePanierById(Integer id) {
		lignePanierRepository.deleteById(id);

	}

	@Override
	public int getRealLignePanierById(Integer materielId, Integer panierId) {
		return lignePanierRepository.getByMaterialPanier(materielId, panierId);
	}

	@Override
	public Set<LignePanier> getLPByMaterial(Materiel m) {
		return lignePanierRepository.getByMaterialRef(m);
	}
//
//	@Override
//	public LignePanier getLignePanierByIdPanier(Integer idPanier) {
//		return lignePanierRepository.getLignePanierByPAnierID(idPanier);
//	}
//
//	@Override
//	public List<LignePanier> getLignePanierByIdPanier(Integer idPanier) {
//		return lignePanierRepository.getLignePanierByPAnierID(idPanier);
//	}

}
