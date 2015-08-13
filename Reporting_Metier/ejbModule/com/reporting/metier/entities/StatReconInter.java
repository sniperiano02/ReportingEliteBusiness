package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the stat_recon_inter database table.
 * 
 */
@Entity
@Table(name="stat_recon_inter",schema="stat")
@NamedQuery(name="StatReconInter.findAll", query="SELECT s FROM StatReconInter s")
public class StatReconInter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="code_operateur",referencedColumnName="code_operateurs",insertable=false,updatable=false)
	private OperateurInterco operateur ;
	
	public OperateurInterco getOperateur() {
		return operateur;
	}
	public void setOperateur(OperateurInterco operateur) {
		this.operateur = operateur;
	}
	private String anomalie;

	@Column(name="code_operateur")
	private String codeOperateur;

	@Column(name="date_appel")
	private String dateAppel;

	@Column(name="duree_anomalie")
	private BigDecimal dureeAnomalie;

	@Column(name="duree_cti")
	private BigDecimal dureeCti;

	@Column(name="duree_intec")
	private BigDecimal dureeIntec;

	@Column(name="id_pays")
	private Long idPays;

	@Column(name="montant_anomalie")
	private BigDecimal montantAnomalie;

	@Column(name="montant_cti")
	private BigDecimal montantCti;

	@Column(name="montant_intec")
	private BigDecimal montantIntec;

	@Column(name="nb_anomalie")
	private BigDecimal nbAnomalie;

	@Column(name="nb_appel_cti")
	private BigDecimal nbAppelCti;

	@Column(name="nb_appel_intec")
	private BigDecimal nbAppelIntec;

	@Column(name="tranche_horaire")
	private String trancheHoraire;

	@Column(name="type_stat")
	private String typeStat;

	@Column(name="type_trafic")
	private String typeTrafic;

	public StatReconInter() {
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

	public BigDecimal getDureeAnomalie() {
		return this.dureeAnomalie;
	}

	public void setDureeAnomalie(BigDecimal dureeAnomalie) {
		this.dureeAnomalie = dureeAnomalie;
	}

	public BigDecimal getDureeCti() {
		return this.dureeCti;
	}

	public void setDureeCti(BigDecimal dureeCti) {
		this.dureeCti = dureeCti;
	}

	public BigDecimal getDureeIntec() {
		return this.dureeIntec;
	}

	public void setDureeIntec(BigDecimal dureeIntec) {
		this.dureeIntec = dureeIntec;
	}

	public Long getIdPays() {
		return this.idPays;
	}

	public void setIdPays(Long idPays) {
		this.idPays = idPays;
	}

	public BigDecimal getMontantAnomalie() {
		return this.montantAnomalie;
	}

	public void setMontantAnomalie(BigDecimal montantAnomalie) {
		this.montantAnomalie = montantAnomalie;
	}

	public BigDecimal getMontantCti() {
		return this.montantCti;
	}

	public void setMontantCti(BigDecimal montantCti) {
		this.montantCti = montantCti;
	}

	public BigDecimal getMontantIntec() {
		return this.montantIntec;
	}

	public void setMontantIntec(BigDecimal montantIntec) {
		this.montantIntec = montantIntec;
	}

	public BigDecimal getNbAnomalie() {
		return this.nbAnomalie;
	}

	public void setNbAnomalie(BigDecimal nbAnomalie) {
		this.nbAnomalie = nbAnomalie;
	}

	public BigDecimal getNbAppelCti() {
		return this.nbAppelCti;
	}

	public void setNbAppelCti(BigDecimal nbAppelCti) {
		this.nbAppelCti = nbAppelCti;
	}

	public BigDecimal getNbAppelIntec() {
		return this.nbAppelIntec;
	}

	public void setNbAppelIntec(BigDecimal nbAppelIntec) {
		this.nbAppelIntec = nbAppelIntec;
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

	public String getTypeTrafic() {
		return this.typeTrafic;
	}

	public void setTypeTrafic(String typeTrafic) {
		this.typeTrafic = typeTrafic;
	}

}