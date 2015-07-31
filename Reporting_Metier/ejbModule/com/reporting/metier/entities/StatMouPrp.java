package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the stat_mou_prp database table.
 * 
 */
@Entity
@Table(name="stat_mou_prp",schema="stat")
@NamedQuery(name="StatMouPrp.findAll", query="SELECT s FROM StatMouPrp s")
public class StatMouPrp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="date_appel")
	private String dateAppel;

	@Column(name="nb_minutes")
	private BigDecimal nbMinutes;

	@Column(name="parc_prp")
	private BigDecimal parcPrp;

	public StatMouPrp() {
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

	public BigDecimal getNbMinutes() {
		return this.nbMinutes;
	}

	public void setNbMinutes(BigDecimal nbMinutes) {
		this.nbMinutes = nbMinutes;
	}

	public BigDecimal getParcPrp() {
		return this.parcPrp;
	}

	public void setParcPrp(BigDecimal parcPrp) {
		this.parcPrp = parcPrp;
	}

}