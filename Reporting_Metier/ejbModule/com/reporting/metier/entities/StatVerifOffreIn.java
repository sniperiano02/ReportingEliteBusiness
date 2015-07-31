package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the stat_verif_offre_in database table.
 * 
 */
@Entity
@Table(name="stat_verif_offre_in",schema="stat")
@NamedQuery(name="StatVerifOffreIn.findAll", query="SELECT s FROM StatVerifOffreIn s")
public class StatVerifOffreIn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	

	@ManyToOne ( fetch = FetchType.LAZY)
	@JoinColumn(name="offre",referencedColumnName="id_offre")
	private Offre offre;
	
	
	private String anomalie;

	@Column(name="date_appel")
	private String dateAppel;

	@Column(name="nb_appel")
	private BigDecimal nbAppel;



	@Column(name="tarif_appliquer")
	private BigDecimal tarifAppliquer;

	@Column(name="tarif_commercial")
	private BigDecimal tarifCommercial;

	public StatVerifOffreIn() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public BigDecimal getNbAppel() {
		return this.nbAppel;
	}

	public void setNbAppel(BigDecimal nbAppel) {
		this.nbAppel = nbAppel;
	}

	public Offre getOffre() {
		return offre;
	}
	public void setOffre(Offre offre) {
		this.offre = offre;
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