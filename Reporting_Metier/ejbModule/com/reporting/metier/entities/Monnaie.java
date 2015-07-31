package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the monnaie database table.
 * 
 */
@Entity
@Table(schema="tableref")
@NamedQuery(name="Monnaie.findAll", query="SELECT m FROM Monnaie m")
public class Monnaie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;

	@Column(name="code_monnai")
	private String codeMonnai;

	private String monnai;

	public Monnaie() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodeMonnai() {
		return this.codeMonnai;
	}

	public void setCodeMonnai(String codeMonnai) {
		this.codeMonnai = codeMonnai;
	}

	public String getMonnai() {
		return this.monnai;
	}

	public void setMonnai(String monnai) {
		this.monnai = monnai;
	}

}