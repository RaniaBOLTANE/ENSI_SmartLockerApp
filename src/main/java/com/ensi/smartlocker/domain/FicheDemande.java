package com.ensi.smartlocker.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Proxy;

import com.ensi.smartlocker.domain.enumeration.EtatDemande;

@Entity
@Proxy(lazy = false)
public class FicheDemande extends Fiche implements Serializable {

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private EtatDemande etatDemande;

	@OneToMany(mappedBy = "ficheDemande", cascade = CascadeType.ALL)
	private Set<LigneFicheDemande> ligneFicheDemandes = new HashSet<LigneFicheDemande>();

	public FicheDemande() {
	}
	public FicheDemande(Calendar dateCreationFiche, User user) {
		super(dateCreationFiche, user);
	}

	public FicheDemande(Calendar dateCreationFiche, User user, EtatDemande etatDemande) {
		super(dateCreationFiche, user);
		this.etatDemande = etatDemande;
	}

	public Set<LigneFicheDemande> getLigneFicheDemandes() {
		return ligneFicheDemandes;
	}

	public void setLigneFicheDemandes(Set<LigneFicheDemande> ligneFicheDemandes) {
		this.ligneFicheDemandes = ligneFicheDemandes;
	}

	public void addLigneFicheDemande(LigneFicheDemande ligneFicheDemande) {
		this.ligneFicheDemandes.add(ligneFicheDemande);
	}

}
