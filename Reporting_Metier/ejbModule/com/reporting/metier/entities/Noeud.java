package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the noeud database table.
 * 
 */
@Entity
@Table(name="noeud",schema="tableref")
@NamedQuery(name="Noeud.findAll", query="SELECT n FROM Noeud n")
public class Noeud implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="code_noeud")
	private String codeNoeud;

	@Column(name="date_modif")
	private Timestamp dateModif;

	private String identifiant;

	private String nom;

	@Column(name="nom_utilisateur")
	private String nomUtilisateur;

	private String type;

	public Noeud() {
	}

	public String getCodeNoeud() {
		return this.codeNoeud;
	}

	public void setCodeNoeud(String codeNoeud) {
		this.codeNoeud = codeNoeud;
	}

	public Timestamp getDateModif() {
		return this.dateModif;
	}

	public void setDateModif(Timestamp dateModif) {
		this.dateModif = dateModif;
	}

	public String getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
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