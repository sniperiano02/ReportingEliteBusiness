package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the parametres_fraudes database table.
 * 
 */
@Entity
@Table(name="parametres_fraudes",schema="tableref")
@NamedQuery(name="ParametresFraude.findAll", query="SELECT p FROM ParametresFraude p")
public class ParametresFraude implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;

	@Column(name="nom_parametre")
	private String nomParametre;

	public ParametresFraude() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomParametre() {
		return this.nomParametre;
	}

	public void setNomParametre(String nomParametre) {
		this.nomParametre = nomParametre;
	}

}