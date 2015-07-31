package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.math.BigDecimal;


/**
 * The persistent class for the stat_recharge database table.
 * 
 */
@Entity
@Table(name="stat_recharge",schema="stat")
@NamedQuery(name="StatRecharge.findAll", query="SELECT s FROM StatRecharge s")
public class StatRecharge implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="bonus_recharge")
	private BigDecimal bonusRecharge;

	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name="code_plan_tarifaire",referencedColumnName="code_plan_tarifaire")
	private PlanTarifaire plan_tarifaire;

	

	@Column(name="date_appel")
	private String dateAppel;

	@Column(name="nb_recharge")
	private BigDecimal nbRecharge;

	@Column(name="recharge_total")
	private BigDecimal rechargeTotal;

	@Column(name="tranche_horaire")
	private String trancheHoraire;

	@Column(name="type_recharge")
	private String typeRecharge;

	@Column(name="valeur_carte")
	private BigDecimal valeurCarte;

	public StatRecharge() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getBonusRecharge() {
		return this.bonusRecharge;
	}

	public void setBonusRecharge(BigDecimal bonusRecharge) {
		this.bonusRecharge = bonusRecharge;
	}

	public PlanTarifaire getPlan_tarifaire() {
		return plan_tarifaire;
	}
	public void setPlan_tarifaire(PlanTarifaire plan_tarifaire) {
		this.plan_tarifaire = plan_tarifaire;
	}
	public String getDateAppel() {
		return this.dateAppel;
	}

	public void setDateAppel(String dateAppel) {
		this.dateAppel = dateAppel;
	}

	public BigDecimal getNbRecharge() {
		return this.nbRecharge;
	}

	public void setNbRecharge(BigDecimal nbRecharge) {
		this.nbRecharge = nbRecharge;
	}

	public BigDecimal getRechargeTotal() {
		return this.rechargeTotal;
	}

	public void setRechargeTotal(BigDecimal rechargeTotal) {
		this.rechargeTotal = rechargeTotal;
	}

	public String getTrancheHoraire() {
		return this.trancheHoraire;
	}

	public void setTrancheHoraire(String trancheHoraire) {
		this.trancheHoraire = trancheHoraire;
	}

	public String getTypeRecharge() {
		return this.typeRecharge;
	}

	public void setTypeRecharge(String typeRecharge) {
		this.typeRecharge = typeRecharge;
	}

	public BigDecimal getValeurCarte() {
		return this.valeurCarte;
	}

	public void setValeurCarte(BigDecimal valeurCarte) {
		this.valeurCarte = valeurCarte;
	}

}