package com.ensi.smartlocker.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Proxy;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "idlock") })
@Proxy(lazy = false)
public class Casier implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int idlock;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "referenceMateriel")
	private Materiel materiel;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Calendar dater;

	private int quantite;

	
	public Casier() {
	}

	public Casier(Materiel materiel, int quantite, Calendar dater) {
		this.materiel = materiel;
		this.quantite = quantite;
		this.dater = dater;
	}

	public int getIdLock() {
		return idlock;
	}

	public void setIdLock(int idlock) {
		this.idlock = idlock;
	}

	public Materiel getMateriel() {
		return materiel;
	}

	public void setMateriel(Materiel materiel) {
		this.materiel = materiel;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Calendar getDate() {
		return dater;
	}

	public void setDate(Calendar dater) {
		this.dater = dater;
	}

}
