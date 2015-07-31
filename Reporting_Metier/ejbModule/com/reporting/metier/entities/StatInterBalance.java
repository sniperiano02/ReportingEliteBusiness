package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the stat_inter_balance database table.
 * 
 */
@Entity
@Table(name="stat_inter_balance",schema="stat")
@NamedQuery(name="StatInterBalance.findAll", query="SELECT s FROM StatInterBalance s")
public class StatInterBalance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private BigDecimal charge;

	@Column(name="charge_converti")
	private BigDecimal chargeConverti;

	@Column(name="code_operateur")
	private String codeOperateur;

	@Column(name="date_appel")
	private String dateAppel;

	@Column(name="minutes_charge")
	private BigDecimal minutesCharge;

	@Column(name="minutes_produit")
	private BigDecimal minutesProduit;

	@Column(name="nb_charge")
	private BigDecimal nbCharge;

	@Column(name="nb_produit")
	private BigDecimal nbProduit;

	private BigDecimal produit;

	@Column(name="produit_converti")
	private BigDecimal produitConverti;

	public StatInterBalance() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getCharge() {
		return this.charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	public BigDecimal getChargeConverti() {
		return this.chargeConverti;
	}

	public void setChargeConverti(BigDecimal chargeConverti) {
		this.chargeConverti = chargeConverti;
	}

	public String getCodeOperateur() {
		return this.codeOperateur;
	}

	public void setCodeOperateur(String codeOperateur) {
		this.codeOperateur = codeOperateur;
	}

	public String getDateAppel() {
		return this.dateAppel;
	}

	public void setDateAppel(String dateAppel) {
		this.dateAppel = dateAppel;
	}

	public BigDecimal getMinutesCharge() {
		return this.minutesCharge;
	}

	public void setMinutesCharge(BigDecimal minutesCharge) {
		this.minutesCharge = minutesCharge;
	}

	public BigDecimal getMinutesProduit() {
		return this.minutesProduit;
	}

	public void setMinutesProduit(BigDecimal minutesProduit) {
		this.minutesProduit = minutesProduit;
	}

	public BigDecimal getNbCharge() {
		return this.nbCharge;
	}

	public void setNbCharge(BigDecimal nbCharge) {
		this.nbCharge = nbCharge;
	}

	public BigDecimal getNbProduit() {
		return this.nbProduit;
	}

	public void setNbProduit(BigDecimal nbProduit) {
		this.nbProduit = nbProduit;
	}

	public BigDecimal getProduit() {
		return this.produit;
	}

	public void setProduit(BigDecimal produit) {
		this.produit = produit;
	}

	public BigDecimal getProduitConverti() {
		return this.produitConverti;
	}

	public void setProduitConverti(BigDecimal produitConverti) {
		this.produitConverti = produitConverti;
	}

}