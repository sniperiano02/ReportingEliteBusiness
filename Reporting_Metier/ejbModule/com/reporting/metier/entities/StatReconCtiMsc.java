package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.math.BigDecimal;


/**
 * The persistent class for the stat_recon_cti_msc database table.
 * 
 */
@Entity
@Table(name="stat_recon_cti_msc",schema="stat")
@NamedQuery(name="StatReconCtiMsc.findAll", query="SELECT s FROM StatReconCtiMsc s")
public class StatReconCtiMsc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@ManyToOne 
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name="dest",referencedColumnName="id",insertable=false,updatable=false)
	private InterDest destination;
	
	@Column(name="date_appel")
	private String dateAppel;

	private Long dest;

	@Column(name="duree_appel_cti")
	private BigDecimal dureeAppelCti;

	@Column(name="duree_appel_msc")
	private BigDecimal dureeAppelMsc;

	@Column(name="duree_roa_out")
	private BigDecimal dureeRoaOut;

	@Column(name="nb_appel_cti")
	private BigDecimal nbAppelCti;

	@Column(name="nb_appel_msc")
	private BigDecimal nbAppelMsc;

	@Column(name="nb_roa_out")
	private BigDecimal nbRoaOut;

	@Column(name="tranche_horaire")
	private String trancheHoraire;

	public StatReconCtiMsc() {
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

	public Long getDest() {
		return this.dest;
	}

	public void setDest(Long dest) {
		this.dest = dest;
	}

	public BigDecimal getDureeAppelCti() {
		return this.dureeAppelCti;
	}

	public void setDureeAppelCti(BigDecimal dureeAppelCti) {
		this.dureeAppelCti = dureeAppelCti;
	}

	public BigDecimal getDureeAppelMsc() {
		return this.dureeAppelMsc;
	}

	public void setDureeAppelMsc(BigDecimal dureeAppelMsc) {
		this.dureeAppelMsc = dureeAppelMsc;
	}

	public BigDecimal getDureeRoaOut() {
		return this.dureeRoaOut;
	}

	public void setDureeRoaOut(BigDecimal dureeRoaOut) {
		this.dureeRoaOut = dureeRoaOut;
	}

	public BigDecimal getNbAppelCti() {
		return this.nbAppelCti;
	}

	public void setNbAppelCti(BigDecimal nbAppelCti) {
		this.nbAppelCti = nbAppelCti;
	}

	public BigDecimal getNbAppelMsc() {
		return this.nbAppelMsc;
	}

	public void setNbAppelMsc(BigDecimal nbAppelMsc) {
		this.nbAppelMsc = nbAppelMsc;
	}

	public BigDecimal getNbRoaOut() {
		return this.nbRoaOut;
	}

	public void setNbRoaOut(BigDecimal nbRoaOut) {
		this.nbRoaOut = nbRoaOut;
	}

	public String getTrancheHoraire() {
		return this.trancheHoraire;
	}

	public void setTrancheHoraire(String trancheHoraire) {
		this.trancheHoraire = trancheHoraire;
	}
	public InterDest getDestination() {
		return destination;
	}
	public void setPays(InterDest destination) {
		this.destination = destination;
	}

}