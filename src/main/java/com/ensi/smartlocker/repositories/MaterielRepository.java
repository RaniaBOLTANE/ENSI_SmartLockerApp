package com.ensi.smartlocker.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ensi.smartlocker.domain.Materiel;

public interface MaterielRepository extends JpaRepository<Materiel, Integer> {

	@Query("SELECT m FROM Materiel m WHERE m.designationMateriel = :designationMateriel")
	Materiel getByName(@Param("designationMateriel") String name);

	@Query("SELECT m FROM Materiel m WHERE m.statusMateriel = 'OutOfStock'")
	Set<Materiel> getByStatusOOS();

}
