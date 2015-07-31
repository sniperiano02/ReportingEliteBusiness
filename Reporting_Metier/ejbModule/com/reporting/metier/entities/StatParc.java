package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the stat_parc database table.
 * 
 */
@Entity
@Table(name="stat_parc",schema="stat")
@NamedQuery(name="StatParc.findAll", query="SELECT s FROM StatParc s")
public class StatParc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="date_appel")
	private String dateAppel;

	@Column(name="de_parc")
	private BigDecimal deParc;

	@Column(name="nb_parc")
	private Long nbParc;

	@Column(name="nv_parc")
	private BigDecimal nvParc;

	@Column(name="type_subscriber")
	private String typeSubscriber;

	public StatParc() {
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

	public BigDecimal getDeParc() {
		return this.deParc;
	}

	public void setDeParc(BigDecimal deParc) {
		this.deParc = deParc;
	}

	public Long getNbParc() {
		return this.nbParc;
	}

	public void setNbParc(Long nbParc) {
		this.nbParc = nbParc;
	}

	public BigDecimal getNvParc() {
		return this.nvParc;
	}

	public void setNvParc(BigDecimal nvParc) {
		this.nvParc = nvParc;
	}

	public String getTypeSubscriber() {
		return this.typeSubscriber;
	}

	public void setTypeSubscriber(String typeSubscriber) {
		this.typeSubscriber = typeSubscriber;
	}

}