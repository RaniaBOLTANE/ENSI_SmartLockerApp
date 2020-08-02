package com.ensi.smartlocker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ensi.smartlocker.domain.Casier;
import com.ensi.smartlocker.domain.Categorie;
import com.ensi.smartlocker.domain.Materiel;

public interface CasierRepository extends JpaRepository<Casier, Integer> {
	
	@Query("SELECT c FROM Casier c WHERE c.materiel.referenceMateriel = :referenceMateriel")
	Casier getByreference(@Param("referenceMateriel") int referenceMateriel);

}
