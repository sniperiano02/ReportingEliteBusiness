package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the stat_intec_natinterco database table.
 * 
 */
@Entity
@Table(name="stat_intec_natinterco",schema="stat")
@NamedQuery(name="StatIntecNatinterco.findAll", query="SELECT s FROM StatIntecNatinterco s")
public class StatIntecNatinterco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@ManyToOne ( fetch = FetchType.LAZY)
	@JoinColumn(name="code_operateur",referencedColumnName="code_operateurs",insertable=false,updatable=false)
	private OperateurInterco operateur;
	
	@Column(name="code_operateur")
	private String codeOperateur;

	@Column(name="date_appel")
	private String dateAppel;

	private BigDecimal duree;

	@Column(name="moyenne_duree")
	private BigDecimal moyenneDuree;

	@Column(name="nb_appel")
	private BigDecimal nbAppel;

	@Column(name="nb_moyenne")
	private BigDecimal nbMoyenne;

	@Column(name="nom_destination")
	private String nomDestination;

	private BigDecimal revenue;

	@Column(name="revenue_moyenne")
	private BigDecimal revenueMoyenne;

	@Column(name="tranche_horaire")
	private String trancheHoraire;

	@Column(name="type_stat")
	private String typeStat;

	public StatIntecNatinterco() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodeOperateur() {
		return this.codeOperateur;
	}

	public void setCodeOperateur(String codeOperateur) {
		this.codeOperateur = codeOperateur;
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

	public String getNomDestination() {
		return this.nomDestination;
	}

	public void setNomDestination(String nomDestination) {
		this.nomDestination = nomDestination;
	}

	public BigDecimal getRevenue() {
		return this.revenue;
	}

	public void setRevenue(BigDecimal revenue) {
		this.revenue = revenue;
	}

	public BigDecimal getRevenueMoyenne() {
		return this.revenueMoyenne;
	}

	public void setRevenueMoyenne(BigDecimal revenueMoyenne) {
		this.revenueMoyenne = revenueMoyenne;
	}

	public String getTrancheHoraire() {
		return this.trancheHoraire;
	}

	public void setTrancheHoraire(String trancheHoraire) {
		this.trancheHoraire = trancheHoraire;
	}

	public String getTypeStat() {
		return this.typeStat;
	}

	public void setTypeStat(String typeStat) {
		this.typeStat = typeStat;
	}

}