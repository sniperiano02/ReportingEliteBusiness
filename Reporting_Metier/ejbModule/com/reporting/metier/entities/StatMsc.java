package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.math.BigDecimal;


/**
 * The persistent class for the stat_msc database table.
 * 
 */
@Entity
@Table(name="stat_msc",schema="stat")
@NamedQuery(name="StatMsc.findAll", query="SELECT s FROM StatMsc s")
public class StatMsc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	
	@ManyToOne ( fetch = FetchType.LAZY)
	@JoinColumn(name="type_dest",referencedColumnName="id",insertable=false,updatable=false)
	private TypeDestination destination;

	
	@Column(name="date_appel")
	private String dateAppel;

	private BigDecimal duree;

	@Column(name="moyenne_duree")
	private BigDecimal moyenneDuree;

	private String msc;

	@Column(name="nb_appel")
	private BigDecimal nbAppel;

	@Column(name="nb_moyenne")
	private BigDecimal nbMoyenne;

	@Column(name="tranche_horaire")
	private String trancheHoraire;

	@Column(name="type_call")
	private String typeCall;

	
	@Column(name="type_subscriber")
	private String typeSubscriber;

	public StatMsc() {
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

	public String getMsc() {
		return this.msc;
	}

	public void setMsc(String msc) {
		this.msc = msc;
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


	public String getTypeSubscriber() {
		return this.typeSubscriber;
	}

	public void setTypeSubscriber(String typeSubscriber) {
		this.typeSubscriber = typeSubscriber;
	}

	
 public TypeDestination getDestination() {
		return destination;
	}

	public void setDestination(TypeDestination destination) {
		this.destination = destination;
	}

}