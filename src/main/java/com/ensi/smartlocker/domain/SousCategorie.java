package com.ensi.smartlocker.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy=false)
public class SousCategorie implements Serializable {

	@Id
	@Column(length = 45, nullable = false, unique = true)
	private String sousCategorie;

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(
			name = "Souscategorie_Materiel",
			joinColumns = @JoinColumn(name = "sousCategorie"),
			inverseJoinColumns = @JoinColumn(name = "referenceMateriel")
	)
	private Set<Materiel> Materiels = new HashSet<Materiel>();

	public SousCategorie() {
		super();
	}

	public SousCategorie(String sousCategorie, Categorie categorie) {
		super();
		this.sousCategorie = sousCategorie;
	}

	public String getSousCategorie() {
		return sousCategorie;
	}

	public void setSousCategorie(String sousCategorie) {
		this.sousCategorie = sousCategorie;
	}


	public Set<Materiel> getMateriels() {
		return Materiels;
	}

	public void setMateriels(Set<Materiel> materiels) {
		Materiels = materiels;
	}
	
	public void addMateriel(Materiel materiel) {
		this.Materiels.add(materiel);
	}
	
	public void removeMateriel(Materiel materiel) {
		this.Materiels.remove(materiel);
	}

}
