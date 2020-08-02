package com.ensi.smartlocker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ensi.smartlocker.domain.Categorie;

public interface CategorieRepository extends JpaRepository<Categorie, String> {

	@Query(value = "SELECT categorie FROM categorie_souscategorie WHERE sous_categorie = ?1", nativeQuery = true)
	Categorie getBySousCategorieId(String souscategorie);

}
