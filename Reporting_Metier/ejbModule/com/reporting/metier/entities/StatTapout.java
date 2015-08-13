package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the stat_tapout database table.
 * 
 */
@Entity
@Table(name="stat_tapout",schema="stat")
@NamedQuery(name="StatTapout.findAll", query="SELECT s FROM StatTapout s")
public class StatTapout implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="code_operateur")
	private String codeOperateur;

	@Column(name="date_appel")
	private String dateAppel;

	private String dategeneration;

	private BigDecimal duree;

	@Column(name="id_operateur")
	private Long idOperateur;

	@Column(name="nb_appel")
	private BigDecimal nbAppel;

	private BigDecimal revenue;

	@Column(name="revenue_converti")
	private BigDecimal revenueConverti;

	@Column(name="type_call")
	private String typeCall;

	public StatTapout() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodeOperateur() {
		return this.codeOperateur;
	}

	public void setCodeOperateur(String codeOperateur) {
		this.codeOperateur = codeOperateur;
	}

	public String getDateAppel() {
		return this.dateAppel;
	}

	public void setDateAppel(String dateAppel) {
		this.dateAppel = dateAppel;
	}

	public String getDategeneration() {
		return this.dategeneration;
	}

	public void setDategeneration(String dategeneration) {
		this.dategeneration = dategeneration;
	}

	public BigDecimal getDuree() {
		return this.duree;
	}

	public void setDuree(BigDecimal duree) {
		this.duree = duree;
	}

	public Long getIdOperateur() {
		return this.idOperateur;
	}

	public void setIdOperateur(Long idOperateur) {
		this.idOperateur = idOperateur;
	}

	public BigDecimal getNbAppel() {
		return this.nbAppel;
	}

	public void setNbAppel(BigDecimal nbAppel) {
		this.nbAppel = nbAppel;
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

	public String getTypeCall() {
		return this.typeCall;
	}

	public void setTypeCall(String typeCall) {
		this.typeCall = typeCall;
	}

}