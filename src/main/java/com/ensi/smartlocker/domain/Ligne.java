package com.ensi.smartlocker.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Ligne {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int idLigne;
	
	@Column(nullable = false)
	private int quantiteMateriel;

	public Ligne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ligne(int quantiteMateriel) {
		super();
		this.quantiteMateriel = quantiteMateriel;
	}

	public int getIdLigne() {
		return idLigne;
	}

	public void setIdLigne(int idLigne) {
		this.idLigne = idLigne;
	}

	public int getQuantiteMateriel() {
		return quantiteMateriel;
	}

	public void setQuantiteMateriel(int quantiteMateriel) {
		this.quantiteMateriel = quantiteMateriel;
	}

}
