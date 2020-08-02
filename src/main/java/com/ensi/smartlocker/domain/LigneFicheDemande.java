package com.ensi.smartlocker.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.ensi.smartlocker.domain.enumeration.StatusMateriel;

@Entity
@Table
@Proxy(lazy = false)
public class LigneFicheDemande extends Ligne {

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "referenceMateriel")
	private Materiel materiel;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idFiche")
	private FicheDemande ficheDemande;

	@Enumerated(EnumType.STRING)
	@Column(length = 25, nullable = false)
	private StatusMateriel statusMateriel;

	public LigneFicheDemande() {
		
	}

	public LigneFicheDemande(int quantiteMateriel) {
		super(quantiteMateriel);
	}

	public LigneFicheDemande(Materiel materiel, FicheDemande ficheDemande, StatusMateriel statusMateriel) {
		super();
		this.materiel = materiel;
		this.ficheDemande = ficheDemande;
		this.statusMateriel = statusMateriel;
	}

	public Materiel getMateriel() {
		return materiel;
	}

	public void setMateriel(Materiel materiel) {
		this.materiel = materiel;
	}

	public FicheDemande getFicheDemande() {
		return ficheDemande;
	}

	public void setFicheDemande(FicheDemande ficheDemande) {
		this.ficheDemande = ficheDemande;
	}

	public StatusMateriel getStatusMateriel() {
		return statusMateriel;
	}

	public void setStatusMateriel(StatusMateriel statusMateriel) {
		this.statusMateriel = statusMateriel;
	}

}
