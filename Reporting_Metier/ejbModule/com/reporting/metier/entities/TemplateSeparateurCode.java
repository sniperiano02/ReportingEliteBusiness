package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the template_separateur_code database table.
 * 
 */
@Entity
@Table(name="template_separateur_code",schema="tables")
@NamedQuery(name="TemplateSeparateurCode.findAll", query="SELECT t FROM TemplateSeparateurCode t")
public class TemplateSeparateurCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;

	@Column(name="nom_template")
	private String nomTemplate;

	private String separateur;

	public TemplateSeparateurCode() {
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

	public String getSeparateur() {
		return this.separateur;
	}

	public void setSeparateur(String separateur) {
		this.separateur = separateur;
	}

}