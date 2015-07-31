package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the template_feuille database table.
 * 
 */
@Entity
@Table(name="template_feuille",schema="tables")
@NamedQuery(name="TemplateFeuille.findAll", query="SELECT t FROM TemplateFeuille t")
public class TemplateFeuille implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;

	private Integer feuille;

	
	@ManyToOne
	@JoinColumn(name="id_template",referencedColumnName="id")
	private Template template;
	
	

	
	
	
	public Template getTemplate() {
		return template;
	}
	public void setTemplate(Template template) {
		this.template = template;
	}
	@Column(name="nb_ligne")
	private Integer nbLigne;

	@Column(name="nom_template")
	private String nomTemplate;

	@Column(name="type_feuille")
	private String typeFeuille;

	public TemplateFeuille() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFeuille() {
		return this.feuille;
	}

	public void setFeuille(Integer feuille) {
		this.feuille = feuille;
	}

	public Integer getNbLigne() {
		return this.nbLigne;
	}

	public void setNbLigne(Integer nbLigne) {
		this.nbLigne = nbLigne;
	}

	public String getNomTemplate() {
		return this.nomTemplate;
	}

	public void setNomTemplate(String nomTemplate) {
		this.nomTemplate = nomTemplate;
	}

	public String getTypeFeuille() {
		return this.typeFeuille;
	}

	public void setTypeFeuille(String typeFeuille) {
		this.typeFeuille = typeFeuille;
	}

}