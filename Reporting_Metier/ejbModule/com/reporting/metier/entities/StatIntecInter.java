package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the stat_intec_inter database table.
 * 
 */
@Entity
@Table(name="stat_intec_inter",schema="stat")
@NamedQuery(name="StatIntecInter.findAll", query="SELECT s FROM StatIntecInter s")
public class StatIntecInter implements Serializable {
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
	@Column(name="code_operateur")
	private String codeOperateur;

	@Column(name="date_appel")
	private String dateAppel;

	@Column(name="duree_moyenne")
	private BigDecimal dureeMoyenne;

	@Column(name="duree_total")
	private BigDecimal dureeTotal;

	@Column(name="montant_converti")
	private BigDecimal montantConverti;

	@Column(name="montant_total")
	private BigDecimal montantTotal;

	@Column(name="nb_moyenne")
	private BigDecimal nbMoyenne;

	@Column(name="nombre_total")
	private BigDecimal nombreTotal;

	@Column(name="revenue_moyenne")
	private BigDecimal revenueMoyenne;

	@Column(name="tranche_horaire")
	private String trancheHoraire;

	@Column(name="type_stat")
	private String typeStat;

	@Column(name="type_trafic")
	private String typeTrafic;

	public StatIntecInter() {
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

	public BigDecimal getMontantConverti() {
		return this.montantConverti;
	}

	public void setMontantConverti(BigDecimal montantConverti) {
		this.montantConverti = montantConverti;
	}

	public BigDecimal getMontantTotal() {
		return this.montantTotal;
	}

	public void setMontantTotal(BigDecimal montantTotal) {
		this.montantTotal = montantTotal;
	}

	public BigDecimal getNbMoyenne() {
		return this.nbMoyenne;
	}

	public void setNbMoyenne(BigDecimal nbMoyenne) {
		this.nbMoyenne = nbMoyenne;
	}

	public BigDecimal getNombreTotal() {
		return this.nombreTotal;
	}

	public void setNombreTotal(BigDecimal nombreTotal) {
		this.nombreTotal = nombreTotal;
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

	public String getTypeTrafic() {
		return this.typeTrafic;
	}

	public void setTypeTrafic(String typeTrafic) {
		this.typeTrafic = typeTrafic;
	}

}