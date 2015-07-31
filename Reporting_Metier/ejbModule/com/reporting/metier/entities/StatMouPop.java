package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the stat_mou_pop database table.
 * 
 */
@Entity
@Table(name="stat_mou_pop",schema="stat")
@NamedQuery(name="StatMouPop.findAll", query="SELECT s FROM StatMouPop s")
public class StatMouPop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="date_appel")
	private String dateAppel;

	@Column(name="nb_minutes")
	private BigDecimal nbMinutes;

	@Column(name="parc_pop")
	private BigDecimal parcPop;

	public StatMouPop() {
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

	public BigDecimal getParcPop() {
		return this.parcPop;
	}

	public void setParcPop(BigDecimal parcPop) {
		this.parcPop = parcPop;
	}

}