package com.ensi.smartlocker.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false)
public class LigneFicheEmprunt extends Ligne {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "referenceMateriel")
	private Materiel materiel;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idFiche")
	private FicheEmprunt ficheEmprunt;

	public LigneFicheEmprunt(int quantiteMateriel) {
		super(quantiteMateriel);
	}

	public LigneFicheEmprunt(int quantiteMateriel, Materiel materiel, FicheEmprunt ficheEmprunt) {
		super(quantiteMateriel);
		this.materiel = materiel;
		this.ficheEmprunt = ficheEmprunt;
	}


	public LigneFicheEmprunt() {
		// TODO Auto-generated constructor stub
	}

	public Materiel getMateriel() {
		return materiel;
	}

	public void setMateriel(Materiel materiel) {
		this.materiel = materiel;
	}

	public FicheEmprunt getFicheEmprunt() {
		return ficheEmprunt;
	}

	public void setFicheEmprunt(FicheEmprunt ficheEmprunt) {
		this.ficheEmprunt = ficheEmprunt;
	}

}
