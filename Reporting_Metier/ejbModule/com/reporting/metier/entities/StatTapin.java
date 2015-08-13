package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the stat_tapin database table.
 * 
 */
@Entity
@Table(name="stat_tapin",schema="stat")
@NamedQuery(name="StatTapin.findAll", query="SELECT s FROM StatTapin s")
public class StatTapin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="charge_converti")
	private BigDecimal chargeConverti;

	@Column(name="charge_total")
	private BigDecimal chargeTotal;

	@Column(name="code_tadig")
	private String codeTadig;

	@Column(name="date_appel")
	private String dateAppel;

	@Column(name="date_generation")
	private String dateGeneration;

	@Column(name="duree_total")
	private BigDecimal dureeTotal;

	@Column(name="id_operateur")
	private Long idOperateur;

	@Column(name="nombre_total")
	private BigDecimal nombreTotal;

	@Column(name="type_call")
	private String typeCall;

	@Column(name="type_subscriber")
	private String typeSubscriber;

	public StatTapin() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getChargeConverti() {
		return this.chargeConverti;
	}

	public void setChargeConverti(BigDecimal chargeConverti) {
		this.chargeConverti = chargeConverti;
	}

	public BigDecimal getChargeTotal() {
		return this.chargeTotal;
	}

	public void setChargeTotal(BigDecimal chargeTotal) {
		this.chargeTotal = chargeTotal;
	}

	public String getCodeTadig() {
		return this.codeTadig;
	}

	public void setCodeTadig(String codeTadig) {
		this.codeTadig = codeTadig;
	}

	public String getDateAppel() {
		return this.dateAppel;
	}

	public void setDateAppel(String dateAppel) {
		this.dateAppel = dateAppel;
	}

	public String getDateGeneration() {
		return this.dateGeneration;
	}

	public void setDateGeneration(String dateGeneration) {
		this.dateGeneration = dateGeneration;
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

	public BigDecimal getNombreTotal() {
		return this.nombreTotal;
	}

	public void setNombreTotal(BigDecimal nombreTotal) {
		this.nombreTotal = nombreTotal;
	}

	public String getTypeCall() {
		return this.typeCall;
	}

	public void setTypeCall(String typeCall) {
		this.typeCall = typeCall;
	}

	public String getTypeSubscriber() {
		return this.typeSubscriber;
	}

	public void setTypeSubscriber(String typeSubscriber) {
		this.typeSubscriber = typeSubscriber;
	}

}