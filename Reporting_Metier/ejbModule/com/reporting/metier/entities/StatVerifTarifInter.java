package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the stat_verif_tarif_inter database table.
 * 
 */
@Entity
@Table(name="stat_verif_tarif_inter",schema="stat")
@NamedQuery(name="StatVerifTarifInter.findAll", query="SELECT s FROM StatVerifTarifInter s")
public class StatVerifTarifInter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="date_appel")
	private String dateAppel;

	private BigDecimal diff;

	private BigDecimal duree;

	@Column(name="id_pays")
	private Long idPays;

	private BigDecimal montant;

	@Column(name="montant_intec")
	private BigDecimal montantIntec;

	@Column(name="nb_appel")
	private BigDecimal nbAppel;

	@Column(name="nom_destination")
	private String nomDestination;

	private String operateur;

	@Column(name="type_stat")
	private String typeStat;

	@Column(name="type_trafic")
	private String typeTrafic;

	public StatVerifTarifInter() {
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

	public BigDecimal getDiff() {
		return this.diff;
	}

	public void setDiff(BigDecimal diff) {
		this.diff = diff;
	}

	public BigDecimal getDuree() {
		return this.duree;
	}

	public void setDuree(BigDecimal duree) {
		this.duree = duree;
	}

	public Long getIdPays() {
		return this.idPays;
	}

	public void setIdPays(Long idPays) {
		this.idPays = idPays;
	}

	public BigDecimal getMontant() {
		return this.montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	public BigDecimal getMontantIntec() {
		return this.montantIntec;
	}

	public void setMontantIntec(BigDecimal montantIntec) {
		this.montantIntec = montantIntec;
	}

	public BigDecimal getNbAppel() {
		return this.nbAppel;
	}

	public void setNbAppel(BigDecimal nbAppel) {
		this.nbAppel = nbAppel;
	}

	public String getNomDestination() {
		return this.nomDestination;
	}

	public void setNomDestination(String nomDestination) {
		this.nomDestination = nomDestination;
	}

	public String getOperateur() {
		return this.operateur;
	}

	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}

	public String getTypeStat() {
		return this.typeStat;
	}

	public void setTypeStat(String typeStat) {
		this.typeStat = typeStat;
	}

	public String getTypeTrafic() {
		return this.typeTrafic;
	}

	public void setTypeTrafic(String typeTrafic) {
		this.typeTrafic = typeTrafic;
	}

}