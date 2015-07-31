package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the stat_arpu_pop database table.
 * 
 */
@Entity
@Table(name="stat_arpu_pop",schema="stat")
@NamedQuery(name="StatArpuPop.findAll", query="SELECT s FROM StatArpuPop s")
public class StatArpuPop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="date_appel")
	private String dateAppel;

	@Column(name="parc_pop")
	private BigDecimal parcPop;

	@Column(name="revenue_total")
	private BigDecimal revenueTotal;

	public StatArpuPop() {
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

	public BigDecimal getParcPop() {
		return this.parcPop;
	}

	public void setParcPop(BigDecimal parcPop) {
		this.parcPop = parcPop;
	}

	public BigDecimal getRevenueTotal() {
		return this.revenueTotal;
	}

	public void setRevenueTotal(BigDecimal revenueTotal) {
		this.revenueTotal = revenueTotal;
	}

}