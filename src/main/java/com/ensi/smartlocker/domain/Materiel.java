package com.ensi.smartlocker.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Proxy;

import com.ensi.smartlocker.domain.enumeration.MaterialStatus;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "referenceMateriel"),
		@UniqueConstraint(columnNames = "designationMateriel") })
@Proxy(lazy = false)
public class Materiel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private int referenceMateriel;

	@Column(length = 25, nullable = false, unique = true)
	private String designationMateriel;

	@Column(length = 65535, columnDefinition = "Text")
	private String descriptionMateriel;

	private int quantiteGeneraleMateriel;
	private int quantiteDisponibleeMateriel;

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private MaterialStatus statusMateriel;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Calendar dateCreationMateriel;

	public Materiel() {
	}

	public Materiel(int referenceMateriel) {
		super();
		this.referenceMateriel = referenceMateriel;
	}

	public Materiel(String designationMateriel, String descriptionMateriel, int quantiteGeneraleMateriel,
			int quantiteDisponibleeMateriel, Calendar dateCreationMateriel) {
		super();
		this.designationMateriel = designationMateriel;
		this.descriptionMateriel = descriptionMateriel;
		this.quantiteGeneraleMateriel = quantiteGeneraleMateriel;
		this.quantiteDisponibleeMateriel = quantiteDisponibleeMateriel;
		this.dateCreationMateriel = dateCreationMateriel;
	}

	public Materiel(String designationMateriel, String descriptionMateriel, int quantiteGeneraleMateriel,
			int quantiteDisponibleeMateriel, Calendar dateCreationMateriel, Set<LigneFicheEmprunt> ligneFicheEmprunts,
			Set<LigneFicheRetour> ligneFicheRetours, Set<LigneFicheDemande> ligneFicheDemandes) {
		super();
		this.designationMateriel = designationMateriel;
		this.descriptionMateriel = descriptionMateriel;
		this.quantiteGeneraleMateriel = quantiteGeneraleMateriel;
		this.quantiteDisponibleeMateriel = quantiteDisponibleeMateriel;
		this.dateCreationMateriel = dateCreationMateriel;
		// this.ligneFicheEmprunts = ligneFicheEmprunts;
//		this.ligneFicheRetours = ligneFicheRetours;
//		this.ligneFicheDemandes = ligneFicheDemandes;
	}

	public Materiel(int referenceMateriel, String designationMateriel, String descriptionMateriel,
			int quantiteGeneraleMateriel, int quantiteDisponibleeMateriel, Calendar dateCreationMateriel) {
		super();
		this.referenceMateriel = referenceMateriel;
		this.designationMateriel = designationMateriel;
		this.descriptionMateriel = descriptionMateriel;
		this.quantiteGeneraleMateriel = quantiteGeneraleMateriel;
		this.quantiteDisponibleeMateriel = quantiteDisponibleeMateriel;
		this.dateCreationMateriel = dateCreationMateriel;
	}

	public int getReferenceMateriel() {
		return referenceMateriel;
	}

	public void setReferenceMateriel(int referenceMateriel) {
		this.referenceMateriel = referenceMateriel;
	}

	public String getDesignationMateriel() {
		return designationMateriel;
	}

	public void setDesignationMateriel(String designationMateriel) {
		this.designationMateriel = designationMateriel;
	}

	public String getDescriptionMateriel() {
		return descriptionMateriel;
	}

	public void setDescriptionMateriel(String descriptionMateriel) {
		this.descriptionMateriel = descriptionMateriel;
	}

	public int getQuantiteGeneraleMateriel() {
		return quantiteGeneraleMateriel;
	}

	public void setQuantiteGeneraleMateriel(int quantiteGeneraleMateriel) {
		this.quantiteGeneraleMateriel = quantiteGeneraleMateriel;
	}

	public int getQuantiteDisponibleeMateriel() {
		return quantiteDisponibleeMateriel;
	}

	public void setQuantiteDisponibleeMateriel(int quantiteDisponibleeMateriel) {
		this.quantiteDisponibleeMateriel = quantiteDisponibleeMateriel;
	}

	public Calendar getDateCreationMateriel() {
		return dateCreationMateriel;
	}

	public void setDateCreationMateriel(Calendar dateCreationMateriel) {
		this.dateCreationMateriel = dateCreationMateriel;
	}

	public MaterialStatus getStatusMateriel() {
		return statusMateriel;
	}

	public void setStatusMateriel(MaterialStatus statusMateriel) {
		this.statusMateriel = statusMateriel;
	}

}
