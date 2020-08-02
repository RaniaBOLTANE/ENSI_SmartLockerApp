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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Proxy;

import com.ensi.smartlocker.domain.enumeration.NiveauUser;
import com.ensi.smartlocker.domain.enumeration.TypeUser;

@Entity
@Proxy(lazy = false)
public class User implements Serializable {

	@Id
	@Column(length = 45)
	private String emailUser;

	@Column(length = 45, nullable = false)
	private String nomUser;

	@Column(length = 45, nullable = false)
	private String prenomUser;

	@Column(length = 8)
	private String numTelUser;

	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	private NiveauUser niveauUser;

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private TypeUser typeUser;

	@Column(length = 60, nullable = false)
	private String passwordUser;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Calendar dateCreationUser;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_ficheemprunt", joinColumns = @JoinColumn(name = "emailUser"), inverseJoinColumns = @JoinColumn(name = "idFiche"))
	private Set<FicheEmprunt> ficheEmprunts = new HashSet<FicheEmprunt>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_ficheretour", joinColumns = @JoinColumn(name = "emailUser"), inverseJoinColumns = @JoinColumn(name = "idFiche"))
	private Set<FicheRetour> ficheRetours = new HashSet<FicheRetour>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_fichedemande", joinColumns = @JoinColumn(name = "emailUser"), inverseJoinColumns = @JoinColumn(name = "idFiche"))
	private Set<FicheDemande> ficheDemandes = new HashSet<FicheDemande>();

	public User() {
		super();
	}

	public User(String emailUser) {
		super();
		this.emailUser = emailUser;
	}

	public User(String emailUser, String nomUser, String prenomUser, String numTelUser, NiveauUser niveauUser,
			TypeUser typeUser, String passwordUser, Calendar dateCreationUser) {
		super();
		this.emailUser = emailUser;
		this.nomUser = nomUser;
		this.prenomUser = prenomUser;
		this.numTelUser = numTelUser;
		this.niveauUser = niveauUser;
		this.typeUser = typeUser;
		this.passwordUser = passwordUser;
		this.dateCreationUser = dateCreationUser;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getNomUser() {
		return nomUser;
	}

	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}

	public String getPrenomUser() {
		return prenomUser;
	}

	public void setPrenomUser(String prenomUser) {
		this.prenomUser = prenomUser;
	}

	public String getNumTelUser() {
		return numTelUser;
	}

	public void setNumTelUser(String numTelUser) {
		this.numTelUser = numTelUser;
	}

	public NiveauUser getNiveauUser() {
		return niveauUser;
	}

	public void setNiveauUser(NiveauUser niveauUser) {
		this.niveauUser = niveauUser;
	}

	public TypeUser getTypeUser() {
		return typeUser;
	}

	public void setTypeUser(TypeUser typeUser) {
		this.typeUser = typeUser;
	}

	public Calendar getDateCreationUser() {
		return dateCreationUser;
	}

	public void setDateCreationUser(Calendar dateCreationUser) {
		this.dateCreationUser = dateCreationUser;
	}

	public String getPasswordUser() {
		return passwordUser;
	}

	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}

	public Set<FicheEmprunt> getFicheEmprunts() {
		return ficheEmprunts;
	}

	public void setFicheEmprunts(Set<FicheEmprunt> ficheEmprunts) {
		this.ficheEmprunts = ficheEmprunts;
	}

	public Set<FicheRetour> getFicheRetours() {
		return ficheRetours;
	}

	public void setFicheRetours(Set<FicheRetour> ficheRetours) {
		this.ficheRetours = ficheRetours;
	}

	public Set<FicheDemande> getFicheDemandes() {
		return ficheDemandes;
	}

	public void setFicheDemandes(Set<FicheDemande> ficheDemandes) {
		this.ficheDemandes = ficheDemandes;
	}

}
