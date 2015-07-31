package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.math.BigDecimal;


/**
 * The persistent class for the stat_trafic_in database table.
 * 
 */
@Entity
@Table(name="stat_trafic_in",schema="stat")
@NamedQuery(name="StatTraficIn.findAll", query="SELECT s FROM StatTraficIn s")
public class StatTraficIn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@ManyToOne ( fetch = FetchType.LAZY)
    @NotFound(
            action = NotFoundAction.IGNORE)
	@JoinColumn(name="type_dest",referencedColumnName="id",insertable=false,updatable=false)
	private TypeDestination destination;
	
	@ManyToOne( fetch = FetchType.LAZY)
    @NotFound(
            action = NotFoundAction.IGNORE)
	@JoinColumn(name="plan_tarifaire",referencedColumnName="code_plan_tarifaire",insertable=false,updatable=false)
	private PlanTarifaire plan;

	@Column(name="date_appel")
	private String dateAppel;

	
	public PlanTarifaire getPlan() {
		return plan;
	}
	public void setPlan(PlanTarifaire plan) {
		this.plan = plan;
	}
	private BigDecimal duree;

	@Column(name="moyenne_duree")
	private BigDecimal moyenneDuree;

	@Column(name="moyenne_revenue")
	private BigDecimal moyenneRevenue;

	@Column(name="nb_appel")
	private BigDecimal nbAppel;

	@Column(name="nb_moyenne")
	private BigDecimal nbMoyenne;

	

	private BigDecimal revenue;

	@Column(name="tranche_horaire")
	private String trancheHoraire;

	@Column(name="type_call")
	private String typeCall;

	@Column(name="type_cdr")
	private String typeCdr;

	@Column(name="type_dest")
	private Integer typeDest;

	
	
	
	public TypeDestination getDestination() {
		return destination;
	}
	public void setDestination(TypeDestination destination) {
		this.destination = destination;
	}
	public StatTraficIn() {
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

	public BigDecimal getDuree() {
		return this.duree;
	}

	public void setDuree(BigDecimal duree) {
		this.duree = duree;
	}

	public BigDecimal getMoyenneDuree() {
		return this.moyenneDuree;
	}

	public void setMoyenneDuree(BigDecimal moyenneDuree) {
		this.moyenneDuree = moyenneDuree;
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

	public String getTypeCall() {
		return this.typeCall;
	}

	public void setTypeCall(String typeCall) {
		this.typeCall = typeCall;
	}

	public String getTypeCdr() {
		return this.typeCdr;
	}

	public void setTypeCdr(String typeCdr) {
		this.typeCdr = typeCdr;
	}

	public Integer getTypeDest() {
		return this.typeDest;
	}

	public void setTypeDest(Integer typeDest) {
		this.typeDest = typeDest;
	}
	


}