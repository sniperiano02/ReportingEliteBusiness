package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the stat_verif_tarif_roamingin database table.
 * 
 */
@Entity
@Table(name="stat_verif_tarif_roamingin",schema="stat")
@NamedQuery(name="StatVerifTarifRoamingin.findAll", query="SELECT s FROM StatVerifTarifRoamingin s")
public class StatVerifTarifRoamingin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="date_appel")
	private String dateAppel;

	private String dest;

	@ManyToOne
	@JoinColumn(name="id_operateur",referencedColumnName="id")
	private RoamingOperator operateur;

	
	private BigDecimal impact;

	@Column(name="nb_appel")
	private BigDecimal nbAppel;

	@Column(name="tarif_appliquer")
	private BigDecimal tarifAppliquer;

	@Column(name="tarif_commmercial")
	private BigDecimal tarifCommmercial;

	@Column(name="type_call")
	private String typeCall;

	public StatVerifTarifRoamingin() {
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

	public String getDest() {
		return this.dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public RoamingOperator getOperateur() {
		return operateur;
	}
	public void setOperateur(RoamingOperator operateur) {
		this.operateur = operateur;
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

	public BigDecimal getTarifAppliquer() {
		return this.tarifAppliquer;
	}

	public void setTarifAppliquer(BigDecimal tarifAppliquer) {
		this.tarifAppliquer = tarifAppliquer;
	}

	public BigDecimal getTarifCommmercial() {
		return this.tarifCommmercial;
	}

	public void setTarifCommmercial(BigDecimal tarifCommmercial) {
		this.tarifCommmercial = tarifCommmercial;
	}

	public String getTypeCall() {
		return this.typeCall;
	}

	public void setTypeCall(String typeCall) {
		this.typeCall = typeCall;
	}

}