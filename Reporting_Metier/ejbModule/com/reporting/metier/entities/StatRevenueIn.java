package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.math.BigDecimal;


/**
 * The persistent class for the stat_revenue_in database table.
 * 
 */
@Entity
@Table(name="stat_revenue_in",schema="stat")
@NamedQuery(name="StatRevenueIn.findAll", query="SELECT s FROM StatRevenueIn s")
public class StatRevenueIn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="balance_revenue")
	private BigDecimal balanceRevenue;

	@Column(name="conso_revenue")
	private BigDecimal consoRevenue;

	@Column(name="date_appel")
	private String dateAppel;

	private BigDecimal diff;

	@Column(name="final_balance")
	private BigDecimal finalBalance;

	@Column(name="initial_balance")
	private BigDecimal initialBalance;

	@Column(name="montant_recharge")
	private BigDecimal montantRecharge;

	@ManyToOne( fetch = FetchType.LAZY)
	  @NotFound(
	            action = NotFoundAction.IGNORE)
	@JoinColumn(name="plan_tarifaire",referencedColumnName="code_plan_tarifaire")
	private PlanTarifaire plan;
	

	@Column(name="transfert_in")
	private BigDecimal transfertIn;

	@Column(name="transfert_out")
	private BigDecimal transfertOut;

	public StatRevenueIn() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getBalanceRevenue() {
		return this.balanceRevenue;
	}

	public void setBalanceRevenue(BigDecimal balanceRevenue) {
		this.balanceRevenue = balanceRevenue;
	}

	public BigDecimal getConsoRevenue() {
		return this.consoRevenue;
	}

	public void setConsoRevenue(BigDecimal consoRevenue) {
		this.consoRevenue = consoRevenue;
	}

	public String getDateAppel() {
		return this.dateAppel;
	}

	public void setDateAppel(String dateAppel) {
		this.dateAppel = dateAppel;
	}

	public BigDecimal getDiff() {
		return this.diff;
	}

	public void setDiff(BigDecimal diff) {
		this.diff = diff;
	}

	public BigDecimal getFinalBalance() {
		return this.finalBalance;
	}

	public void setFinalBalance(BigDecimal finalBalance) {
		this.finalBalance = finalBalance;
	}

	public BigDecimal getInitialBalance() {
		return this.initialBalance;
	}

	public void setInitialBalance(BigDecimal initialBalance) {
		this.initialBalance = initialBalance;
	}

	public BigDecimal getMontantRecharge() {
		return this.montantRecharge;
	}

	public void setMontantRecharge(BigDecimal montantRecharge) {
		this.montantRecharge = montantRecharge;
	}

	public PlanTarifaire getPlan() {
		return plan;
	}
	public void setPlan(PlanTarifaire plan) {
		this.plan = plan;
	}
	public BigDecimal getTransfertIn() {
		return this.transfertIn;
	}

	public void setTransfertIn(BigDecimal transfertIn) {
		this.transfertIn = transfertIn;
	}

	public BigDecimal getTransfertOut() {
		return this.transfertOut;
	}

	public void setTransfertOut(BigDecimal transfertOut) {
		this.transfertOut = transfertOut;
	}

}