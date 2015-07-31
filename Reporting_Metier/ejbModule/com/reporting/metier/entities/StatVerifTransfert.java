package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.math.BigDecimal;


/**
 * The persistent class for the stat_verif_transfert database table.
 * 
 */
@Entity
@Table(name="stat_verif_transfert",schema="stat")
@NamedQuery(name="StatVerifTransfert.findAll", query="SELECT s FROM StatVerifTransfert s")
public class StatVerifTransfert implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="date_appel")
	private String dateAppel;

	private BigDecimal impact;

	@Column(name="nb_appel")
	private BigDecimal nbAppel;
	
	@ManyToOne( fetch = FetchType.LAZY)
	  @NotFound(
	            action = NotFoundAction.IGNORE)
	@JoinColumn(name="plan_tarifaire",referencedColumnName="code_plan_tarifaire")
	private PlanTarifaire plan;
	
	

	@Column(name="tarif_appliquer")
	private BigDecimal tarifAppliquer;

	@Column(name="tarif_commercial")
	private BigDecimal tarifCommercial;

	public StatVerifTransfert() {
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

	public BigDecimal getImpact() {
		return this.impact;
	}

	public void setImpact(BigDecimal impact) {
		this.impact = impact;
	}

	public BigDecimal getNbAppel() {
		return this.nbAppel;
	}

	public void setNbAppel(BigDecimal nbAppel) {
		this.nbAppel = nbAppel;
	}

	public PlanTarifaire getPlan() {
		return plan;
	}
	public void setPlan(PlanTarifaire plan) {
		this.plan = plan;
	}

	public BigDecimal getTarifAppliquer() {
		return this.tarifAppliquer;
	}

	public void setTarifAppliquer(BigDecimal tarifAppliquer) {
		this.tarifAppliquer = tarifAppliquer;
	}

	public BigDecimal getTarifCommercial() {
		return this.tarifCommercial;
	}

	public void setTarifCommercial(BigDecimal tarifCommercial) {
		this.tarifCommercial = tarifCommercial;
	}

}