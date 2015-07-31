package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the template_date_indique database table.
 * 
 */
@Entity
@Table(name="template_date_indique",schema="tables")
@NamedQuery(name="TemplateDateIndique.findAll", query="SELECT t FROM TemplateDateIndique t")
public class TemplateDateIndique implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;

	@Column(name="nom_template")
	private String nomTemplate;

	@Column(name="num_colonne")
	private Long numColonne;

	@Column(name="num_ligne")
	private Long numLigne;

	public TemplateDateIndique() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomTemplate() {
		return this.nomTemplate;
	}

	public void setNomTemplate(String nomTemplate) {
		this.nomTemplate = nomTemplate;
	}

	public Long getNumColonne() {
		return this.numColonne;
	}

	public void setNumColonne(Long numColonne) {
		this.numColonne = numColonne;
	}

	public Long getNumLigne() {
		return this.numLigne;
	}

	public void setNumLigne(Long numLigne) {
		this.numLigne = numLigne;
	}

}