package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the stat_operateur_dest_recon_msc_tapout database table.
 * 
 */
@Entity
@Table(name="stat_operateur_dest_recon_msc_tapout",schema="stat")
@NamedQuery(name="StatOperateurDestReconMscTapout.findAll", query="SELECT s FROM StatOperateurDestReconMscTapout s")
public class StatOperateurDestReconMscTapout implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	
	@ManyToOne
	@JoinColumn(name="code_operateur",referencedColumnName="id")
	private RoamingOperator operateur;

	

	@Column(name="date_appel")
	private String dateAppel;

	
	@ManyToOne
	@JoinColumn(name="dest",referencedColumnName="id")
	private Destination destination;

	

	@Column(name="duree_anomalie")
	private BigDecimal dureeAnomalie;

	@Column(name="duree_total_msc")
	private BigDecimal dureeTotalMsc;

	@Column(name="duree_total_tap")
	private BigDecimal dureeTotalTap;

	private BigDecimal impact;

	@Column(name="kpi_duree")
	private BigDecimal kpiDuree;

	@Column(name="kpi_nb")
	private BigDecimal kpiNb;

	@Column(name="nb_anomalie")
	private BigDecimal nbAnomalie;

	@Column(name="nb_total_msc")
	private BigDecimal nbTotalMsc;

	@Column(name="nb_total_tap")
	private BigDecimal nbTotalTap;

	@Column(name="type_call")
	private String typeCall;

	public StatOperateurDestReconMscTapout() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoamingOperator getOperateur() {
		return operateur;
	}
	public void setOperateur(RoamingOperator operateur) {
		this.operateur = operateur;
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
	public BigDecimal getDureeAnomalie() {
		return this.dureeAnomalie;
	}

	public void setDureeAnomalie(BigDecimal dureeAnomalie) {
		this.dureeAnomalie = dureeAnomalie;
	}

	public BigDecimal getDureeTotalMsc() {
		return this.dureeTotalMsc;
	}

	public void setDureeTotalMsc(BigDecimal dureeTotalMsc) {
		this.dureeTotalMsc = dureeTotalMsc;
	}

	public BigDecimal getDureeTotalTap() {
		return this.dureeTotalTap;
	}

	public void setDureeTotalTap(BigDecimal dureeTotalTap) {
		this.dureeTotalTap = dureeTotalTap;
	}

	public BigDecimal getImpact() {
		return this.impact;
	}

	public void setImpact(BigDecimal impact) {
		this.impact = impact;
	}

	public BigDecimal getKpiDuree() {
		return this.kpiDuree;
	}

	public void setKpiDuree(BigDecimal kpiDuree) {
		this.kpiDuree = kpiDuree;
	}

	public BigDecimal getKpiNb() {
		return this.kpiNb;
	}

	public void setKpiNb(BigDecimal kpiNb) {
		this.kpiNb = kpiNb;
	}

	public BigDecimal getNbAnomalie() {
		return this.nbAnomalie;
	}

	public void setNbAnomalie(BigDecimal nbAnomalie) {
		this.nbAnomalie = nbAnomalie;
	}

	public BigDecimal getNbTotalMsc() {
		return this.nbTotalMsc;
	}

	public void setNbTotalMsc(BigDecimal nbTotalMsc) {
		this.nbTotalMsc = nbTotalMsc;
	}

	public BigDecimal getNbTotalTap() {
		return this.nbTotalTap;
	}

	public void setNbTotalTap(BigDecimal nbTotalTap) {
		this.nbTotalTap = nbTotalTap;
	}

	public String getTypeCall() {
		return this.typeCall;
	}

	public void setTypeCall(String typeCall) {
		this.typeCall = typeCall;
	}

}