package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the stat_arpu_prp database table.
 * 
 */
@Entity
@Table(name="stat_arpu_prp",schema="stat")
@NamedQuery(name="StatArpuPrp.findAll", query="SELECT s FROM StatArpuPrp s")
public class StatArpuPrp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="date_appel")
	private String dateAppel;

	@Column(name="parc_prp")
	private BigDecimal parcPrp;

	@Column(name="revenue_total")
	private BigDecimal revenueTotal;

	public StatArpuPrp() {
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

	public BigDecimal getParcPrp() {
		return this.parcPrp;
	}

	public void setParcPrp(BigDecimal parcPrp) {
		this.parcPrp = parcPrp;
	}

	public BigDecimal getRevenueTotal() {
		return this.revenueTotal;
	}

	public void setRevenueTotal(BigDecimal revenueTotal) {
		this.revenueTotal = revenueTotal;
	}

}