package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the decision_fraude database table.
 * 
 */
@Entity
@Table(name="decision_fraude",schema="stat")
@NamedQuery(name="DecisionFraude.findAll", query="SELECT d FROM DecisionFraude d")
public class DecisionFraude implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="date_decision", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp dateDecision;

	@Column(name="date_modif")
	private Timestamp dateModif;

	private String decision;

	private String msisdn;

	@Column(name="nom_utilisateur")
	private String nomUtilisateur;

	public DecisionFraude() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getDateDecision() {
		return this.dateDecision;
	}

	public void setDateDecision(Timestamp dateDecision) {
		this.dateDecision = dateDecision;
	}

	public Timestamp getDateModif() {
		return this.dateModif;
	}

	public void setDateModif(Timestamp dateModif) {
		this.dateModif = dateModif;
	}

	public String getDecision() {
		return this.decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getMsisdn() {
		return this.msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getNomUtilisateur() {
		return this.nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

}