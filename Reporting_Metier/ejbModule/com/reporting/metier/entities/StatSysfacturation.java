package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.math.BigDecimal;


/**
 * The persistent class for the stat_sysfacturation database table.
 * 
 */
@Entity
@Table(name="stat_sysfacturation",schema="stat")
@NamedQuery(name="StatSysfacturation.findAll", query="SELECT s FROM StatSysfacturation s")
public class StatSysfacturation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@ManyToOne 
    @NotFound(
            action = NotFoundAction.IGNORE)
	@JoinColumn(name="dest",referencedColumnName="id")
	private Destination destination;
	
	@Column(name="date_appel")
	private String dateAppel;

	

	private BigDecimal duree;

	@Column(name="duree_moyenne")
	private BigDecimal dureeMoyenne;

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

	@ManyToOne 
    @NotFound(
            action = NotFoundAction.IGNORE)
	@JoinColumn(name="type_dest",referencedColumnName="id")
	private TypeDestination typeDestination;
	
	public StatSysfacturation() {
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

	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public BigDecimal getDuree() {
		return this.duree;
	}

	public void setDuree(BigDecimal duree) {
		this.duree = duree;
	}

	public BigDecimal getDureeMoyenne() {
		return this.dureeMoyenne;
	}

	public void setDureeMoyenne(BigDecimal dureeMoyenne) {
		this.dureeMoyenne = dureeMoyenne;
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

	public TypeDestination getTypeDestination() {
		return typeDestination;
	}
	public void setTypeDestination(TypeDestination typeDestination) {
		this.typeDestination = typeDestination;
	}
}