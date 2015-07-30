package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the plan_tarifaire database table.
 * 
 */
@Entity
@Table(name="plan_tarifaire",schema="tableref")
@NamedQuery(name="PlanTarifaire.findAll", query="SELECT p FROM PlanTarifaire p")
public class PlanTarifaire implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="code_plan_tarifaire")
	private String codePlanTarifaire;

	@Column(name="date_modif")
	private Timestamp dateModif;

	@Column(name="nom_utilisateur")
	private String nomUtilisateur;

	@Column(name="plan_tarifaire")
	@ToUse
	private String planTarifaire;

	private String type;

	public PlanTarifaire() {
	}

	public String getCodePlanTarifaire() {
		return this.codePlanTarifaire;
	}

	public void setCodePlanTarifaire(String codePlanTarifaire) {
		this.codePlanTarifaire = codePlanTarifaire;
	}

	public Timestamp getDateModif() {
		return this.dateModif;
	}

	public void setDateModif(Timestamp dateModif) {
		this.dateModif = dateModif;
	}

	public String getNomUtilisateur() {
		return this.nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getPlanTarifaire() {
		return this.planTarifaire;
	}

	public void setPlanTarifaire(String planTarifaire) {
		this.planTarifaire = planTarifaire;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}