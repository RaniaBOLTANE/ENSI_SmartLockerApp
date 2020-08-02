package com.ensi.smartlocker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ensi.smartlocker.domain.Panier;
import com.ensi.smartlocker.domain.SousCategorie;

public interface PanierRepository extends JpaRepository<Panier, Integer> {

	@Query(value = "SELECT id_panier FROM panier WHERE email_user = ?1", nativeQuery = true)
	int getByUserEmail(String email);

}
