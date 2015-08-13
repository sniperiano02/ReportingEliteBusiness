package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the parc_roa_out database table.
 * 
 */
@Entity
@Table(name="parc_roa_out",schema="stat")
@NamedQuery(name="ParcRoaOut.findAll", query="SELECT p FROM ParcRoaOut p")
public class ParcRoaOut implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="date_appel")
	private String dateAppel;

	@Column(name="duree_total")
	private BigDecimal dureeTotal;

	@Column(name="id_operateur")
	private Long idOperateur;

	private BigDecimal parc;

	private BigDecimal revenue;

	@Column(name="revenue_converti")
	private BigDecimal revenueConverti;

	@Column(name="type_subscriber")
	private String typeSubscriber;

	public ParcRoaOut() {
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

	public BigDecimal getDureeTotal() {
		return this.dureeTotal;
	}

	public void setDureeTotal(BigDecimal dureeTotal) {
		this.dureeTotal = dureeTotal;
	}

	public Long getIdOperateur() {
		return this.idOperateur;
	}

	public void setIdOperateur(Long idOperateur) {
		this.idOperateur = idOperateur;
	}

	public BigDecimal getParc() {
		return this.parc;
	}

	public void setParc(BigDecimal parc) {
		this.parc = parc;
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

	public String getTypeSubscriber() {
		return this.typeSubscriber;
	}

	public void setTypeSubscriber(String typeSubscriber) {
		this.typeSubscriber = typeSubscriber;
	}

}