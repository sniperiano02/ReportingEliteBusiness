package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the stat_rec_tapin database table.
 * 
 */
@Entity
@Table(name="stat_rec_tapin",schema="stat")
@NamedQuery(name="StatRecTapin.findAll", query="SELECT s FROM StatRecTapin s")
public class StatRecTapin implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@Id
	private Integer id;

	@ManyToOne ( fetch = FetchType.LAZY)
	@JoinColumn(name="code_operateur",referencedColumnName="id")
	private RoamingOperator operateur;
	
	private BigDecimal charge;

	

	@Column(name="date_appel")
	private String dateAppel;

	@Column(name="duree_pop")
	private BigDecimal dureePop;

	@Column(name="duree_prepaye")
	private BigDecimal dureePrepaye;

	@Column(name="duree_tapin")
	private BigDecimal dureeTapin;

	@Column(name="nb_pop")
	private BigDecimal nbPop;

	@Column(name="nb_prepaye")
	private BigDecimal nbPrepaye;

	@Column(name="nb_tapin")
	private BigDecimal nbTapin;

	@Column(name="revenue_pop")
	private BigDecimal revenuePop;

	@Column(name="revenue_prepaye")
	private BigDecimal revenuePrepaye;

	@Column(name="type_call")
	private String typeCall;

	public StatRecTapin() {
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

	

	public String getDateAppel() {
		return this.dateAppel;
	}

	public void setDateAppel(String dateAppel) {
		this.dateAppel = dateAppel;
	}

	public BigDecimal getDureePop() {
		return this.dureePop;
	}

	public void setDureePop(BigDecimal dureePop) {
		this.dureePop = dureePop;
	}

	public BigDecimal getDureePrepaye() {
		return this.dureePrepaye;
	}

	public void setDureePrepaye(BigDecimal dureePrepaye) {
		this.dureePrepaye = dureePrepaye;
	}

	public BigDecimal getDureeTapin() {
		return this.dureeTapin;
	}

	public void setDureeTapin(BigDecimal dureeTapin) {
		this.dureeTapin = dureeTapin;
	}

	public BigDecimal getNbPop() {
		return this.nbPop;
	}

	public void setNbPop(BigDecimal nbPop) {
		this.nbPop = nbPop;
	}

	public BigDecimal getNbPrepaye() {
		return this.nbPrepaye;
	}

	public void setNbPrepaye(BigDecimal nbPrepaye) {
		this.nbPrepaye = nbPrepaye;
	}

	public BigDecimal getNbTapin() {
		return this.nbTapin;
	}

	public void setNbTapin(BigDecimal nbTapin) {
		this.nbTapin = nbTapin;
	}

	public BigDecimal getRevenuePop() {
		return this.revenuePop;
	}

	public void setRevenuePop(BigDecimal revenuePop) {
		this.revenuePop = revenuePop;
	}

	public BigDecimal getRevenuePrepaye() {
		return this.revenuePrepaye;
	}

	public void setRevenuePrepaye(BigDecimal revenuePrepaye) {
		this.revenuePrepaye = revenuePrepaye;
	}

	public String getTypeCall() {
		return this.typeCall;
	}

	public void setTypeCall(String typeCall) {
		this.typeCall = typeCall;
	}

}