package com.ensi.smartlocker.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false)
public class FicheEmprunt extends Fiche implements Serializable {

	@OneToMany(mappedBy = "ficheEmprunt", cascade = CascadeType.ALL)
	private Set<LigneFicheEmprunt> ligneFicheEmprunts = new HashSet<LigneFicheEmprunt>();

	public FicheEmprunt(Calendar dateCreationFiche,User user) {
		super(dateCreationFiche,user);
	}
	public FicheEmprunt() {
	}
	
	public FicheEmprunt(Calendar dateCreationFiche,User user, Set<LigneFicheEmprunt> ligneFicheEmprunts) {
		super(dateCreationFiche,user);
		this.ligneFicheEmprunts = ligneFicheEmprunts;
	}


	public Set<LigneFicheEmprunt> getLigneFicheEmprunts() {
		return ligneFicheEmprunts;
	}

	public void setLigneFicheEmprunts(Set<LigneFicheEmprunt> ligneFicheEmprunts) {
		this.ligneFicheEmprunts = ligneFicheEmprunts;
	}

	public void addLigneFicheEmprunt(LigneFicheEmprunt ligneFicheEmprunt) {
		this.ligneFicheEmprunts.add(ligneFicheEmprunt);
	}

}
