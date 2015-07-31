package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the stat_erreur database table.
 * 
 */
@Entity
@Table(name="stat_erreur",schema="stat")
@NamedQuery(name="StatErreur.findAll", query="SELECT s FROM StatErreur s")
public class StatErreur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_stat_erreur")
	private Integer idStatErreur;

	@Column(name="code_anomalie")
	private String codeAnomalie;

	private String erreur;

	@Column(name="max_date")
	private String maxDate;

	@Column(name="min_date")
	private String minDate;

	@Column(name="nb_cdr")
	private String nbCdr;

	private String noeud;

	
	@ManyToOne
	@JoinColumn(name="plan_tarifaire",referencedColumnName="code_plan_tarifaire")
	private PlanTarifaire planTarifaire;
	
	@Column(name="type_flux")
	private String typeFlux;

	public StatErreur() {
	}

	
	public PlanTarifaire getPlanTarifaire() {
		return planTarifaire;
	}
	public void setPlanTarifaire(PlanTarifaire planTarifaire) {
		this.planTarifaire = planTarifaire;
	}
	public Integer getIdStatErreur() {
		return this.idStatErreur;
	}

	public void setIdStatErreur(Integer idStatErreur) {
		this.idStatErreur = idStatErreur;
	}

	public String getCodeAnomalie() {
		return this.codeAnomalie;
	}

	public void setCodeAnomalie(String codeAnomalie) {
		this.codeAnomalie = codeAnomalie;
	}

	public String getErreur() {
		return this.erreur;
	}

	public void setErreur(String erreur) {
		this.erreur = erreur;
	}

	public String getMaxDate() {
		return this.maxDate;
	}

	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}

	public String getMinDate() {
		return this.minDate;
	}

	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}

	public String getNbCdr() {
		return this.nbCdr;
	}

	public void setNbCdr(String nbCdr) {
		this.nbCdr = nbCdr;
	}

	public String getNoeud() {
		return this.noeud;
	}

	public void setNoeud(String noeud) {
		this.noeud = noeud;
	}

	

	public String getTypeFlux() {
		return this.typeFlux;
	}

	public void setTypeFlux(String typeFlux) {
		this.typeFlux = typeFlux;
	}

}