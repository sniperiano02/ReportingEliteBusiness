package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the stat_parc_act_prp database table.
 * 
 */
@Entity
@Table(name="stat_parc_act_prp",schema="stat")
@NamedQuery(name="StatParcActPrp.findAll", query="SELECT s FROM StatParcActPrp s")
public class StatParcActPrp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="date_appel")
	private String dateAppel;

	@Column(name="nb_active")
	private BigDecimal nbActive;

	@Column(name="parc_prp")
	private BigDecimal parcPrp;

	public StatParcActPrp() {
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

	public BigDecimal getNbActive() {
		return this.nbActive;
	}

	public void setNbActive(BigDecimal nbActive) {
		this.nbActive = nbActive;
	}

	public BigDecimal getParcPrp() {
		return this.parcPrp;
	}

	public void setParcPrp(BigDecimal parcPrp) {
		this.parcPrp = parcPrp;
	}

}