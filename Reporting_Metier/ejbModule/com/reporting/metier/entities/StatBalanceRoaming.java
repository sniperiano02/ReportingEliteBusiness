package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the stat_balance_roaming database table.
 * 
 */
@Entity
@Table(name="stat_balance_roaming",schema="stat")
@NamedQuery(name="StatBalanceRoaming.findAll", query="SELECT s FROM StatBalanceRoaming s")
public class StatBalanceRoaming implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private BigDecimal charge;

	@Column(name="charge_converti")
	private BigDecimal chargeConverti;

	

	@ManyToOne
	@JoinColumn(name="code_operateur",referencedColumnName="id")
	private RoamingOperator operateur;
	


	@Column(name="code_tadig")
	private String codeTadig;

	@Column(name="date_appel")
	private String dateAppel;

	@Column(name="date_generation")
	private String dateGeneration;

	@Column(name="duree_gprs_charge")
	private BigDecimal dureeGprsCharge;

	@Column(name="duree_gprs_prod")
	private BigDecimal dureeGprsProd;

	@Column(name="duree_moc_charge")
	private BigDecimal dureeMocCharge;

	@Column(name="duree_moc_prod")
	private BigDecimal dureeMocProd;

	@Column(name="duree_mtc_charge")
	private BigDecimal dureeMtcCharge;

	@Column(name="duree_mtc_prod")
	private BigDecimal dureeMtcProd;

	@Column(name="nb_gprs_charge")
	private BigDecimal nbGprsCharge;

	@Column(name="nb_gprs_prod")
	private BigDecimal nbGprsProd;

	@Column(name="nb_moc_charge")
	private BigDecimal nbMocCharge;

	@Column(name="nb_moc_prod")
	private BigDecimal nbMocProd;

	@Column(name="nb_mtc_charge")
	private BigDecimal nbMtcCharge;

	@Column(name="nb_mtc_prod")
	private BigDecimal nbMtcProd;

	private BigDecimal produit;

	@Column(name="produit_converti")
	private BigDecimal produitConverti;

	@Column(name="revenue_gprs_charge")
	private BigDecimal revenueGprsCharge;

	@Column(name="revenue_gprs_prod")
	private BigDecimal revenueGprsProd;

	@Column(name="revenue_moc_charge")
	private BigDecimal revenueMocCharge;

	@Column(name="revenue_moc_prod")
	private BigDecimal revenueMocProd;

	@Column(name="revenue_mtc_charge")
	private BigDecimal revenueMtcCharge;

	@Column(name="revenue_mtc_prod")
	private BigDecimal revenueMtcProd;

	public StatBalanceRoaming() {
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

public RoamingOperator getOperateur() {
	return operateur;
}
public void setOperateur(RoamingOperator operateur) {
	this.operateur = operateur;
}

	public String getCodeTadig() {
		return this.codeTadig;
	}

	public void setCodeTadig(String codeTadig) {
		this.codeTadig = codeTadig;
	}

	public String getDateAppel() {
		return this.dateAppel;
	}

	public void setDateAppel(String dateAppel) {
		this.dateAppel = dateAppel;
	}

	public String getDateGeneration() {
		return this.dateGeneration;
	}

	public void setDateGeneration(String dateGeneration) {
		this.dateGeneration = dateGeneration;
	}

	public BigDecimal getDureeGprsCharge() {
		return this.dureeGprsCharge;
	}

	public void setDureeGprsCharge(BigDecimal dureeGprsCharge) {
		this.dureeGprsCharge = dureeGprsCharge;
	}

	public BigDecimal getDureeGprsProd() {
		return this.dureeGprsProd;
	}

	public void setDureeGprsProd(BigDecimal dureeGprsProd) {
		this.dureeGprsProd = dureeGprsProd;
	}

	public BigDecimal getDureeMocCharge() {
		return this.dureeMocCharge;
	}

	public void setDureeMocCharge(BigDecimal dureeMocCharge) {
		this.dureeMocCharge = dureeMocCharge;
	}

	public BigDecimal getDureeMocProd() {
		return this.dureeMocProd;
	}

	public void setDureeMocProd(BigDecimal dureeMocProd) {
		this.dureeMocProd = dureeMocProd;
	}

	public BigDecimal getDureeMtcCharge() {
		return this.dureeMtcCharge;
	}

	public void setDureeMtcCharge(BigDecimal dureeMtcCharge) {
		this.dureeMtcCharge = dureeMtcCharge;
	}

	public BigDecimal getDureeMtcProd() {
		return this.dureeMtcProd;
	}

	public void setDureeMtcProd(BigDecimal dureeMtcProd) {
		this.dureeMtcProd = dureeMtcProd;
	}

	public BigDecimal getNbGprsCharge() {
		return this.nbGprsCharge;
	}

	public void setNbGprsCharge(BigDecimal nbGprsCharge) {
		this.nbGprsCharge = nbGprsCharge;
	}

	public BigDecimal getNbGprsProd() {
		return this.nbGprsProd;
	}

	public void setNbGprsProd(BigDecimal nbGprsProd) {
		this.nbGprsProd = nbGprsProd;
	}

	public BigDecimal getNbMocCharge() {
		return this.nbMocCharge;
	}

	public void setNbMocCharge(BigDecimal nbMocCharge) {
		this.nbMocCharge = nbMocCharge;
	}

	public BigDecimal getNbMocProd() {
		return this.nbMocProd;
	}

	public void setNbMocProd(BigDecimal nbMocProd) {
		this.nbMocProd = nbMocProd;
	}

	public BigDecimal getNbMtcCharge() {
		return this.nbMtcCharge;
	}

	public void setNbMtcCharge(BigDecimal nbMtcCharge) {
		this.nbMtcCharge = nbMtcCharge;
	}

	public BigDecimal getNbMtcProd() {
		return this.nbMtcProd;
	}

	public void setNbMtcProd(BigDecimal nbMtcProd) {
		this.nbMtcProd = nbMtcProd;
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

	public BigDecimal getRevenueGprsCharge() {
		return this.revenueGprsCharge;
	}

	public void setRevenueGprsCharge(BigDecimal revenueGprsCharge) {
		this.revenueGprsCharge = revenueGprsCharge;
	}

	public BigDecimal getRevenueGprsProd() {
		return this.revenueGprsProd;
	}

	public void setRevenueGprsProd(BigDecimal revenueGprsProd) {
		this.revenueGprsProd = revenueGprsProd;
	}

	public BigDecimal getRevenueMocCharge() {
		return this.revenueMocCharge;
	}

	public void setRevenueMocCharge(BigDecimal revenueMocCharge) {
		this.revenueMocCharge = revenueMocCharge;
	}

	public BigDecimal getRevenueMocProd() {
		return this.revenueMocProd;
	}

	public void setRevenueMocProd(BigDecimal revenueMocProd) {
		this.revenueMocProd = revenueMocProd;
	}

	public BigDecimal getRevenueMtcCharge() {
		return this.revenueMtcCharge;
	}

	public void setRevenueMtcCharge(BigDecimal revenueMtcCharge) {
		this.revenueMtcCharge = revenueMtcCharge;
	}

	public BigDecimal getRevenueMtcProd() {
		return this.revenueMtcProd;
	}

	public void setRevenueMtcProd(BigDecimal revenueMtcProd) {
		this.revenueMtcProd = revenueMtcProd;
	}

}