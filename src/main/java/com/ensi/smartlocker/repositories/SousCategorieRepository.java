package com.ensi.smartlocker.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ensi.smartlocker.domain.SousCategorie;

public interface SousCategorieRepository extends JpaRepository<SousCategorie, String> {

	@Query(value = "SELECT sous_categorie FROM souscategorie_materiel WHERE reference_materiel = ?1", nativeQuery = true)
	SousCategorie getByMaterielId(int referenceMateriel);

	@Transactional
	@Modifying
	@Query(value = "UPDATE souscategorie_materiel sm  SET   sm.sous_categorie = :sousCategorie where sm.reference_materiel = :ref", nativeQuery = true)
	void updateSouscategorieMteriel(@Param("sousCategorie") String souscategorie, @Param("ref") Integer ref);

}
