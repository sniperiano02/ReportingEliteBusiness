package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the proccontrole database table.
 * 
 */
@Entity
@Table(schema="tables")
@NamedQuery(name="Proccontrole.findAll", query="SELECT p FROM Proccontrole p")
public class Proccontrole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="date_ex")
	private Timestamp dateEx;

	@Column(name="date_fex")
	private Timestamp dateFex;

	private BigDecimal etat;

	private String filename;

	private String proc;

	public Proccontrole() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getDateEx() {
		return this.dateEx;
	}

	public void setDateEx(Timestamp dateEx) {
		this.dateEx = dateEx;
	}

	public Timestamp getDateFex() {
		return this.dateFex;
	}

	public void setDateFex(Timestamp dateFex) {
		this.dateFex = dateFex;
	}

	public BigDecimal getEtat() {
		return this.etat;
	}

	public void setEtat(BigDecimal etat) {
		this.etat = etat;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getProc() {
		return this.proc;
	}

	public void setProc(String proc) {
		this.proc = proc;
	}

}