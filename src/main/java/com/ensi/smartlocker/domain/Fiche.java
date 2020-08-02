package com.ensi.smartlocker.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class Fiche {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int idFiche;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Calendar dateCreationFiche;
	
	@ManyToOne
    @JoinColumn(name = "email")
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Fiche() {
		super();
	}

	public Fiche(Calendar dateCreationFiche, User user) {
		super();
		this.dateCreationFiche = dateCreationFiche;
		this.user = user;
	}

	public int getIdFiche() {
		return idFiche;
	}

	public void setIdFiche(int idFiche) {
		this.idFiche = idFiche;
	}

	public Calendar getDateCreationFiche() {
		return dateCreationFiche;
	}

	public void setDateCreationFiche(Calendar dateCreationFiche) {
		this.dateCreationFiche = dateCreationFiche;
	}

}
