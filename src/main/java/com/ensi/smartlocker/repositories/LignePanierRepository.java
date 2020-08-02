package com.ensi.smartlocker.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ensi.smartlocker.domain.LignePanier;
import com.ensi.smartlocker.domain.Materiel;

public interface LignePanierRepository extends JpaRepository<LignePanier, Integer> {

	@Query(value = "SELECT id_ligne FROM ligne_panier WHERE reference_materiel = ?1 AND id_panier = ?2 ", nativeQuery = true)
	int getByMaterialPanier(int materialId, int panierId);

	@Query("SELECT l FROM LignePanier l WHERE l.materiel = :materiel")
	Set<LignePanier> getByMaterialRef(@Param("materiel") Materiel m);

}
