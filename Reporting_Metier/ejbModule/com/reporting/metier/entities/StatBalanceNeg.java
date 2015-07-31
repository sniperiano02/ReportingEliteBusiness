package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the stat_balance_neg database table.
 * 
 */
@Entity
@Table(name="stat_balance_neg",schema="stat")
@NamedQuery(name="StatBalanceNeg.findAll", query="SELECT s FROM StatBalanceNeg s")
public class StatBalanceNeg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_balance_negatif")
	private Integer idBalanceNegatif;

	@Column(name="date_appel")
	private String dateAppel;

	private BigDecimal impact;

	@Column(name="nb_appel")
	private BigDecimal nbAppel;

	public StatBalanceNeg() {
	}

	public Integer getIdBalanceNegatif() {
		return this.idBalanceNegatif;
	}

	public void setIdBalanceNegatif(Integer idBalanceNegatif) {
		this.idBalanceNegatif = idBalanceNegatif;
	}

	public String getDateAppel() {
		return this.dateAppel;
	}

	public void setDateAppel(String dateAppel) {
		this.dateAppel = dateAppel;
	}

	public BigDecimal getImpact() {
		return this.impact;
	}

	public void setImpact(BigDecimal impact) {
		this.impact = impact;
	}

	public BigDecimal getNbAppel() {
		return this.nbAppel;
	}

	public void setNbAppel(BigDecimal nbAppel) {
		this.nbAppel = nbAppel;
	}

}