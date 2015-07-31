package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the stat_transfert_in database table.
 * 
 */
@Entity
@Table(name="stat_transfert_in",schema="stat")
@NamedQuery(name="StatTransfertIn.findAll", query="SELECT s FROM StatTransfertIn s")
public class StatTransfertIn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="date_appel")
	private String dateAppel;

	@Column(name="montant_transfert")
	private BigDecimal montantTransfert;

	@Column(name="nb_transfert")
	private BigDecimal nbTransfert;

	@ManyToOne
	@JoinColumn(name="plan_tarifaire",referencedColumnName="code_plan_tarifaire")
	private PlanTarifaire plan;
	
	@Column(name="taxe_transfert")
	private BigDecimal taxeTransfert;

	@Column(name="tranche_horaire")
	private String trancheHoraire;

	@Column(name="type_action")
	private String typeAction;

	public StatTransfertIn() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDateAppel() {
		return this.dateAppel;
	}

	public void setDateAppel(String dateAppel) {
		this.dateAppel = dateAppel;
	}

	public BigDecimal getMontantTransfert() {
		return this.montantTransfert;
	}

	public void setMontantTransfert(BigDecimal montantTransfert) {
		this.montantTransfert = montantTransfert;
	}

	public BigDecimal getNbTransfert() {
		return this.nbTransfert;
	}

	public void setNbTransfert(BigDecimal nbTransfert) {
		this.nbTransfert = nbTransfert;
	}

	public PlanTarifaire getPlan() {
		return plan;
	}
	public void setPlan(PlanTarifaire plan) {
		this.plan = plan;
	}

	public BigDecimal getTaxeTransfert() {
		return this.taxeTransfert;
	}

	public void setTaxeTransfert(BigDecimal taxeTransfert) {
		this.taxeTransfert = taxeTransfert;
	}

	public String getTrancheHoraire() {
		return this.trancheHoraire;
	}

	public void setTrancheHoraire(String trancheHoraire) {
		this.trancheHoraire = trancheHoraire;
	}

	public String getTypeAction() {
		return this.typeAction;
	}

	public void setTypeAction(String typeAction) {
		this.typeAction = typeAction;
	}

}