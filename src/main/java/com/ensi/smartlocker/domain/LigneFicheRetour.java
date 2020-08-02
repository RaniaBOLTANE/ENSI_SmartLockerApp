package com.ensi.smartlocker.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false)
public class LigneFicheRetour extends Ligne {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "referenceMateriel")
	private Materiel materiel;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idFiche")
	private FicheRetour ficheRetour;

	public LigneFicheRetour(int quantiteMateriel) {
		super(quantiteMateriel);
		// TODO Auto-generated constructor stub
	}

	public LigneFicheRetour() {

	}

	public LigneFicheRetour(Materiel materiel, FicheRetour ficheRetour) {
		super();
		this.materiel = materiel;
		this.ficheRetour = ficheRetour;
	}

	public Materiel getMateriel() {
		return materiel;
	}

	public void setMateriel(Materiel materiel) {
		this.materiel = materiel;
	}

	public FicheRetour getFicheRetour() {
		return ficheRetour;
	}

	public void setFicheRetour(FicheRetour ficheRetour) {
		this.ficheRetour = ficheRetour;
	}

}
