package com.ensi.smartlocker.domain;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false)
public class LignePanier extends Ligne {

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "referenceMateriel")
	private Materiel materiel;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idPanier")
	private Panier panier;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Calendar dateEmprunt;

	public LignePanier() {
		super();
	}

	public LignePanier(int quantiteMateriel, Calendar dateEmprunt) {
		super(quantiteMateriel);
		this.dateEmprunt = dateEmprunt;
	}

	public Calendar getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(Calendar dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public Materiel getMateriel() {
		return materiel;
	}

	public void setMateriel(Materiel materiel) {
		this.materiel = materiel;
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

}
