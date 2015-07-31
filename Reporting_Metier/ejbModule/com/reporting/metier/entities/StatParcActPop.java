package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the stat_parc_act_pop database table.
 * 
 */
@Entity
@Table(name="stat_parc_act_pop",schema="stat")
@NamedQuery(name="StatParcActPop.findAll", query="SELECT s FROM StatParcActPop s")
public class StatParcActPop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="date_appel")
	private String dateAppel;

	@Column(name="nb_active")
	private BigDecimal nbActive;

	@Column(name="parc_pop")
	private BigDecimal parcPop;

	public StatParcActPop() {
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

	public BigDecimal getParcPop() {
		return this.parcPop;
	}

	public void setParcPop(BigDecimal parcPop) {
		this.parcPop = parcPop;
	}

}