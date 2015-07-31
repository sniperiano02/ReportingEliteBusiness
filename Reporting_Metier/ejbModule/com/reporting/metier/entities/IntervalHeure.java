package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the interval_heure database table.
 * 
 */
@Entity
@Table(name="interval_heure",schema="tableref")
@NamedQuery(name="IntervalHeure.findAll", query="SELECT i FROM IntervalHeure i")
public class IntervalHeure implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="code_interval")
	private String codeInterval;

	@Column(name="date_modif")
	private Timestamp dateModif;

	@Column(name="debut_interval")
	private Integer debutInterval;

	@Column(name="fin_interval")
	private Integer finInterval;

	private String nom;

	@Column(name="nom_utilisateur")
	private String nomUtilisateur;

	public IntervalHeure() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodeInterval() {
		return this.codeInterval;
	}

	public void setCodeInterval(String codeInterval) {
		this.codeInterval = codeInterval;
	}

	public Timestamp getDateModif() {
		return this.dateModif;
	}

	public void setDateModif(Timestamp dateModif) {
		this.dateModif = dateModif;
	}

	public Integer getDebutInterval() {
		return this.debutInterval;
	}

	public void setDebutInterval(Integer debutInterval) {
		this.debutInterval = debutInterval;
	}

	public Integer getFinInterval() {
		return this.finInterval;
	}

	public void setFinInterval(Integer finInterval) {
		this.finInterval = finInterval;
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

}