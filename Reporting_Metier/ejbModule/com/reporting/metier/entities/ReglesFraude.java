package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the regles_fraudes database table.
 * 
 */
@Entity
@Table(name="regles_fraudes",schema="tableref")
@NamedQuery(name="ReglesFraude.findAll", query="SELECT r FROM ReglesFraude r")
public class ReglesFraude implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;

	@Column(name="date_modif")
	private Timestamp dateModif;

	private String description;

	private String etat;

	

	

	
	@ManyToOne
	@JoinColumn(name="id_categorie",referencedColumnName="id")
	private CategoriesFraude categorie;
	
	@ManyToOne
	@JoinColumn(name="id_flux",referencedColumnName="id")
	private FluxCdr flux;
	
	public FluxCdr getFlux() {
		return flux;
	}
	public void setFlux(FluxCdr flux) {
		this.flux = flux;
	}
	public CategoriesFraude getCategorie() {
		return categorie;
	}
	public void setCategorie(CategoriesFraude categorie) {
		this.categorie = categorie;
	}
	

	private String nom;

	@Column(name="nom_utilisateur")
	private String nomUtilisateur;

	private String type;

	public ReglesFraude() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDateModif() {
		return this.dateModif;
	}

	public void setDateModif(Timestamp dateModif) {
		this.dateModif = dateModif;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEtat() {
		return this.etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNomUtilisateur() {
		return this.nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}