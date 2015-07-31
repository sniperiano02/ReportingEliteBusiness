package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;


/**
 * The persistent class for the stat_cti_inter database table.
 * 
 */
@Entity
@Table(name="stat_cti_inter",schema="stat")
@NamedQuery(name="StatCtiInter.findAll", query="SELECT s FROM StatCtiInter s")
public class StatCtiInter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private BigDecimal asr;

	private String central;
	
	@ManyToOne ( fetch = FetchType.LAZY)
	@JoinColumn(name="code_operateur",referencedColumnName="code_operateurs",insertable=false,updatable=false)
	private OperateurInterco operateur;
	

	@Column(name="date_appel")
	private String dateAppel;

	@Column(name="duree_moyenne")
	private BigDecimal dureeMoyenne;

	@Column(name="duree_total")
	private BigDecimal dureeTotal;

	@Column(name="id_pays")
	private Long idPays;

	@Column(name="nb_moyenne")
	private BigDecimal nbMoyenne;

	@Column(name="nombre_reussi")
	private BigDecimal nombreReussi;

	@Column(name="nombre_total")
	private BigDecimal nombreTotal;

	@Column(name="tranche_horaire")
	private String trancheHoraire;

	@Column(name="type_stat")
	private String typeStat;

	@Column(name="type_trafic")
	private String typeTrafic;

	public StatCtiInter() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getAsr() {
		return this.asr;
	}

	public void setAsr(BigDecimal asr) {
		this.asr = asr;
	}

	public String getCentral() {
		return this.central;
	}

	public void setCentral(String central) {
		this.central = central;
	}

	public String getDateAppel() {
		return this.dateAppel;
	}

	public void setDateAppel(String dateAppel) {
		this.dateAppel = dateAppel;
	}

	public BigDecimal getDureeMoyenne() {
		return this.dureeMoyenne;
	}

	public void setDureeMoyenne(BigDecimal dureeMoyenne) {
		this.dureeMoyenne = dureeMoyenne;
	}

	public BigDecimal getDureeTotal() {
		return this.dureeTotal;
	}

	public void setDureeTotal(BigDecimal dureeTotal) {
		this.dureeTotal = dureeTotal;
	}

	public Long getIdPays() {
		return this.idPays;
	}

	public void setIdPays(Long idPays) {
		this.idPays = idPays;
	}

	public BigDecimal getNbMoyenne() {
		return this.nbMoyenne;
	}

	public void setNbMoyenne(BigDecimal nbMoyenne) {
		this.nbMoyenne = nbMoyenne;
	}

	public BigDecimal getNombreReussi() {
		return this.nombreReussi;
	}

	public void setNombreReussi(BigDecimal nombreReussi) {
		this.nombreReussi = nombreReussi;
	}

	public BigDecimal getNombreTotal() {
		return this.nombreTotal;
	}

	public void setNombreTotal(BigDecimal nombreTotal) {
		this.nombreTotal = nombreTotal;
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

	 public OperateurInterco getOperateur() {
			return operateur;
		}

		public void setOperateur(OperateurInterco operateur) {
			this.operateur = operateur;
		}
}