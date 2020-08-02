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
@Proxy(lazy = false)
public class Categorie implements Serializable {

	@Id
	@Column(length = 45, nullable = false, unique = true)
	private String Categorie;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "Categorie_Souscategorie", joinColumns = @JoinColumn(name = "Categorie"), inverseJoinColumns = @JoinColumn(name = "sousCategorie"))
	private Set<SousCategorie> SousCategories = new HashSet<SousCategorie>();

	public Categorie() {
		super();
	}

	public Categorie(String categorie) {
		super();
		Categorie = categorie;
	}

	public Categorie(String categorie, Set<SousCategorie> sousCategories) {
		super();
		Categorie = categorie;
		SousCategories = sousCategories;
	}

	public String getCategorie() {
		return Categorie;
	}

	public void setCategorie(String categorie) {
		Categorie = categorie;
	}

	public Set<SousCategorie> getSousCategories() {
		return SousCategories;
	}

	public void setSousCategories(Set<SousCategorie> sousCategories) {
		SousCategories = sousCategories;
	}

	public void addSousCategorie(SousCategorie sousCategorie) {
		this.SousCategories.add(sousCategorie);
	}

}
