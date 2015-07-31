package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the etat database table.
 * 
 */
@Entity
@Table(schema="tableref")
@NamedQuery(name="Etat.findAll", query="SELECT e FROM Etat e")
public class Etat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="col_lib")
	private String colLib;

	private String desc;

	private BigDecimal etat;

	public Etat() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getColLib() {
		return this.colLib;
	}

	public void setColLib(String colLib) {
		this.colLib = colLib;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public BigDecimal getEtat() {
		return this.etat;
	}

	public void setEtat(BigDecimal etat) {
		this.etat = etat;
	}

}