package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the stat_recon_msc_in database table.
 * 
 */
@Entity
@Table(name="stat_recon_msc_in",schema="stat")
@NamedQuery(name="StatReconMscIn.findAll", query="SELECT s FROM StatReconMscIn s")
public class StatReconMscIn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_stat_recon_msc_in")
	private Integer idStatReconMscIn;

	private String anomalie;

	@Column(name="date_appel")
	private String dateAppel;

	@Column(name="duree_anomalie")
	private BigDecimal dureeAnomalie;

	@Column(name="duree_total_in")
	private BigDecimal dureeTotalIn;

	@Column(name="duree_total_msc")
	private BigDecimal dureeTotalMsc;

	private BigDecimal impact;

	@Column(name="kpi_duree")
	private BigDecimal kpiDuree;

	@Column(name="kpi_nb")
	private BigDecimal kpiNb;

	@Column(name="max_duree")
	private BigDecimal maxDuree;

	@Column(name="min_duree")
	private BigDecimal minDuree;

	@Column(name="nb_anomalie")
	private BigDecimal nbAnomalie;

	@Column(name="nb_total_in")
	private BigDecimal nbTotalIn;

	@Column(name="nb_total_msc")
	private BigDecimal nbTotalMsc;

	public StatReconMscIn() {
	}

	public Integer getIdStatReconMscIn() {
		return this.idStatReconMscIn;
	}

	public void setIdStatReconMscIn(Integer idStatReconMscIn) {
		this.idStatReconMscIn = idStatReconMscIn;
	}

	public String getAnomalie() {
		return this.anomalie;
	}

	public void setAnomalie(String anomalie) {
		this.anomalie = anomalie;
	}

	public String getDateAppel() {
		return this.dateAppel;
	}

	public void setDateAppel(String dateAppel) {
		this.dateAppel = dateAppel;
	}

	public BigDecimal getDureeAnomalie() {
		return this.dureeAnomalie;
	}

	public void setDureeAnomalie(BigDecimal dureeAnomalie) {
		this.dureeAnomalie = dureeAnomalie;
	}

	public BigDecimal getDureeTotalIn() {
		return this.dureeTotalIn;
	}

	public void setDureeTotalIn(BigDecimal dureeTotalIn) {
		this.dureeTotalIn = dureeTotalIn;
	}

	public BigDecimal getDureeTotalMsc() {
		return this.dureeTotalMsc;
	}

	public void setDureeTotalMsc(BigDecimal dureeTotalMsc) {
		this.dureeTotalMsc = dureeTotalMsc;
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

	public BigDecimal getMaxDuree() {
		return this.maxDuree;
	}

	public void setMaxDuree(BigDecimal maxDuree) {
		this.maxDuree = maxDuree;
	}

	public BigDecimal getMinDuree() {
		return this.minDuree;
	}

	public void setMinDuree(BigDecimal minDuree) {
		this.minDuree = minDuree;
	}

	public BigDecimal getNbAnomalie() {
		return this.nbAnomalie;
	}

	public void setNbAnomalie(BigDecimal nbAnomalie) {
		this.nbAnomalie = nbAnomalie;
	}

	public BigDecimal getNbTotalIn() {
		return this.nbTotalIn;
	}

	public void setNbTotalIn(BigDecimal nbTotalIn) {
		this.nbTotalIn = nbTotalIn;
	}

	public BigDecimal getNbTotalMsc() {
		return this.nbTotalMsc;
	}

	public void setNbTotalMsc(BigDecimal nbTotalMsc) {
		this.nbTotalMsc = nbTotalMsc;
	}

}