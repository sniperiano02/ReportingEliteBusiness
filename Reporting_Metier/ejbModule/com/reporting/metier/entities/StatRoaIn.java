package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the stat_roa_in database table.
 * 
 */
@Entity
@Table(name="stat_roa_in",schema="stat")
@NamedQuery(name="StatRoaIn.findAll", query="SELECT s FROM StatRoaIn s")
public class StatRoaIn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="date_appel")
	private String dateAppel;

	

	@ManyToOne
	@JoinColumn(name="id_operateur",referencedColumnName="id")
	private RoamingOperator operateur;
	

	@Column(name="nb_minutes")
	private BigDecimal nbMinutes;

	@Column(name="nb_roa")
	private BigDecimal nbRoa;

	private BigDecimal revenue;

	@Column(name="revenue_converti")
	private BigDecimal revenueConverti;

	public StatRoaIn() {
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

	public RoamingOperator getOperateur() {
		return operateur;
	}
	public void setOperateur(RoamingOperator operateur) {
		this.operateur = operateur;
	}

	public BigDecimal getNbMinutes() {
		return this.nbMinutes;
	}

	public void setNbMinutes(BigDecimal nbMinutes) {
		this.nbMinutes = nbMinutes;
	}

	public BigDecimal getNbRoa() {
		return this.nbRoa;
	}

	public void setNbRoa(BigDecimal nbRoa) {
		this.nbRoa = nbRoa;
	}

	public BigDecimal getRevenue() {
		return this.revenue;
	}

	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}

	public BigDecimal getRevenueConverti() {
		return this.revenueConverti;
	}

	public void setRevenueConverti(BigDecimal revenueConverti) {
		this.revenueConverti = revenueConverti;
	}

}