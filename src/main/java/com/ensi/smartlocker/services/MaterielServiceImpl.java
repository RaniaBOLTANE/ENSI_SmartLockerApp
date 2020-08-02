package com.ensi.smartlocker.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensi.smartlocker.domain.Materiel;
import com.ensi.smartlocker.repositories.MaterielRepository;

@Service
public class MaterielServiceImpl implements MaterielService {

	private MaterielRepository materielRepository;

	@Autowired
	public void setMaterielRepository(MaterielRepository materielRepository) {
		this.materielRepository = materielRepository;
	}

	@Override
	public Iterable<Materiel> getAllMateriels() {
		return materielRepository.findAll();
	}

	@Override
	public Materiel getMaterielById(Integer id) {
		return materielRepository.findById(id).orElse(null);
	}

	@Override
	public Materiel saveMateriel(Materiel materiel) {
		return materielRepository.save(materiel);
	}

	@Override
	public void deleteMaterialById(Integer id) {
		materielRepository.deleteById(id);

	}

	@Override
	public Materiel getMaterielByName(String name) {
		return materielRepository.getByName(name);
	}

	@Override
	public Materiel getRealMaterielById(Integer id) {
		return materielRepository.findById(id).get();
	}

	@Override
	public Set<Materiel> allMaterialOutOfStock() {
		return materielRepository.getByStatusOOS();
	}

}
