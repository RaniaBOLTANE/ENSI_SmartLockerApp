package com.ensi.smartlocker.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false)
public class FicheRetour extends Fiche implements Serializable {

	@OneToMany(mappedBy = "ficheRetour", cascade = CascadeType.ALL)
	private Set<LigneFicheRetour> ligneFicheRetours = new HashSet<LigneFicheRetour>();


	public FicheRetour(Calendar dateCreationFiche,User user) {
		super(dateCreationFiche, user);
		// TODO Auto-generated constructor stub
	}
	public FicheRetour() {
	}
	public FicheRetour(Calendar dateCreationFiche,User user, Set<LigneFicheRetour> ligneFicheRetours) {
		super(dateCreationFiche, user);
		this.ligneFicheRetours = ligneFicheRetours;
	}

	public Set<LigneFicheRetour> getLigneFicheRetours() {
		return ligneFicheRetours;
	}

	public void setLigneFicheRetours(Set<LigneFicheRetour> ligneFicheRetours) {
		this.ligneFicheRetours = ligneFicheRetours;
	}

	public void addLigneFicheRetour(LigneFicheRetour ligneFicheRetour) {
		this.ligneFicheRetours.add(ligneFicheRetour);
	}

}
