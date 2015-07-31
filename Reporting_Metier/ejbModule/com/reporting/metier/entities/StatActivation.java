package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.math.BigDecimal;


/**
 * The persistent class for the stat_activation database table.
 * 
 */
@Entity
@Table(name="stat_activation",schema="stat")
@NamedQuery(name="StatActivation.findAll", query="SELECT s FROM StatActivation s")
public class StatActivation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	
	@ManyToOne
	@JoinColumn(name="code_plan_tarifaire",referencedColumnName="code_plan_tarifaire")
	@LazyCollection(LazyCollectionOption.FALSE)
	private PlanTarifaire plan;

	public void setPlan(PlanTarifaire plan) {
		this.plan = plan;
	}
	public PlanTarifaire getPlan() {
		return plan;
	}
	@Column(name="code_service")
	private String codeService;

	@Column(name="date_appel")
	private String dateAppel;

	@Column(name="moyenne_revenue")
	private BigDecimal moyenneRevenue;

	@Column(name="nb_appel")
	private BigDecimal nbAppel;

	@Column(name="nb_moyenne")
	private BigDecimal nbMoyenne;

	private BigDecimal revenue;

	@Column(name="tranche_horaire")
	private String trancheHoraire;

	public StatActivation() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getCodeService() {
		return this.codeService;
	}

	public void setCodeService(String codeService) {
		this.codeService = codeService;
	}

	public String getDateAppel() {
		return this.dateAppel;
	}

	public void setDateAppel(String dateAppel) {
		this.dateAppel = dateAppel;
	}

	public BigDecimal getMoyenneRevenue() {
		return this.moyenneRevenue;
	}

	public void setMoyenneRevenue(BigDecimal moyenneRevenue) {
		this.moyenneRevenue = moyenneRevenue;
	}

	public BigDecimal getNbAppel() {
		return this.nbAppel;
	}

	public void setNbAppel(BigDecimal nbAppel) {
		this.nbAppel = nbAppel;
	}

	public BigDecimal getNbMoyenne() {
		return this.nbMoyenne;
	}

	public void setNbMoyenne(BigDecimal nbMoyenne) {
		this.nbMoyenne = nbMoyenne;
	}

	public BigDecimal getRevenue() {
		return this.revenue;
	}

	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}

	public String getTrancheHoraire() {
		return this.trancheHoraire;
	}

	public void setTrancheHoraire(String trancheHoraire) {
		this.trancheHoraire = trancheHoraire;
	}

}