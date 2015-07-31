package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.math.BigDecimal;


/**
 * The persistent class for the stat_erreur_tarif_in database table.
 * 
 */
@Entity
@Table(name="stat_erreur_tarif_in",schema="stat")
@NamedQuery(name="StatErreurTarifIn.findAll", query="SELECT s FROM StatErreurTarifIn s")
public class StatErreurTarifIn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="code_operateur")
	private Long codeOperateur;

	@Column(name="date_appel")
	private String dateAppel;
	
	
	@ManyToOne
	@JoinColumn(name="code_operateur",referencedColumnName="id",insertable=false,updatable=false)
	private OperateurInterco operateur ;
	
	
	@ManyToOne( fetch = FetchType.LAZY)
	  @NotFound(
	            action = NotFoundAction.IGNORE)
	@JoinColumn(name="dest",referencedColumnName="id")
	private Destination dest;


	private BigDecimal impact;

	@Column(name="nb_appel")
	private BigDecimal nbAppel;

	
	@ManyToOne( fetch = FetchType.LAZY)
	  @NotFound(
	            action = NotFoundAction.IGNORE)
	@JoinColumn(name="plan_tarifaire",referencedColumnName="code_plan_tarifaire")
	private PlanTarifaire plan;
	

	@Column(name="tarif_applique")
	private BigDecimal tarifApplique;

	@Column(name="tarif_commercial")
	private BigDecimal tarifCommercial;

	@Column(name="type_call")
	private String typeCall;

	@Column(name="type_cdr")
	private String typeCdr;

	public StatErreurTarifIn() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getCodeOperateur() {
		return this.codeOperateur;
	}

	public void setCodeOperateur(Long codeOperateur) {
		this.codeOperateur = codeOperateur;
	}

	public String getDateAppel() {
		return this.dateAppel;
	}

	public void setDateAppel(String dateAppel) {
		this.dateAppel = dateAppel;
	}

	public Destination getDest() {
		return dest;
	}
	public void setDest(Destination dest) {
		this.dest = dest;
	}
	public void setPlan(PlanTarifaire plan) {
		this.plan = plan;
	}
	public PlanTarifaire getPlan() {
		return plan;
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

	

	public BigDecimal getTarifApplique() {
		return this.tarifApplique;
	}

	public void setTarifApplique(BigDecimal tarifApplique) {
		this.tarifApplique = tarifApplique;
	}

	public BigDecimal getTarifCommercial() {
		return this.tarifCommercial;
	}

	public void setTarifCommercial(BigDecimal tarifCommercial) {
		this.tarifCommercial = tarifCommercial;
	}

	public String getTypeCall() {
		return this.typeCall;
	}

	public void setTypeCall(String typeCall) {
		this.typeCall = typeCall;
	}

	public String getTypeCdr() {
		return this.typeCdr;
	}

	public void setTypeCdr(String typeCdr) {
		this.typeCdr = typeCdr;
	}

}