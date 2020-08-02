package com.ensi.smartlocker.services;

import java.util.Set;

import com.ensi.smartlocker.domain.Materiel;

public interface MaterielService {

	Iterable<Materiel> getAllMateriels();

	Materiel getMaterielById(Integer id);

	Materiel getMaterielByName(String name);

	Materiel saveMateriel(Materiel materiel);

	Materiel getRealMaterielById(Integer id);

	Set<Materiel> allMaterialOutOfStock();

	void deleteMaterialById(Integer id);

}
