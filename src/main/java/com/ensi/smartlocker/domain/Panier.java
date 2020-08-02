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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Proxy;

import com.ensi.smartlocker.domain.enumeration.EtatPanier;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "idPanier") })
@Proxy(lazy = false)
public class Panier implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int idPanier;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "emailUser")
	private User user;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Calendar dateCreationPnier;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Calendar dateDerniereModification;

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private EtatPanier etatpanier;

	@OneToMany(mappedBy = "panier", cascade = CascadeType.PERSIST)
	private Set<LignePanier> lignePaniers = new HashSet<LignePanier>();

	public Panier() {
	}

	public Calendar getDateCreationPnier() {
		return dateCreationPnier;
	}

	public void setDateCreationPnier(Calendar dateCreationPnier) {
		this.dateCreationPnier = dateCreationPnier;
	}

	public Calendar getDateDerniereModification() {
		return dateDerniereModification;
	}

	public void setDateDerniereModification(Calendar dateDerniereModification) {
		this.dateDerniereModification = dateDerniereModification;
	}

	public EtatPanier getEtatpanier() {
		return etatpanier;
	}

	public void setEtatpanier(EtatPanier etatpanier) {
		this.etatpanier = etatpanier;
	}

	public Set<LignePanier> getLignePaniers() {
		return lignePaniers;
	}

	public void setLignePaniers(Set<LignePanier> lignePaniers) {
		this.lignePaniers = lignePaniers;
	}

	public void addLignePanier(LignePanier lignePanier) {
		this.lignePaniers.add(lignePanier);
	}
	
	public void removeLignePanier(LignePanier lignePanier) {
		this.lignePaniers.remove(lignePanier);
	}

	public int getIdPanier() {
		return idPanier;
	}

	public void setIdPanier(int idPanier) {
		this.idPanier = idPanier;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
