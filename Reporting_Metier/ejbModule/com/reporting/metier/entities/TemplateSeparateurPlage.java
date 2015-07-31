package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the template_separateur_plage database table.
 * 
 */
@Entity
@Table(name="template_separateur_plage",schema="tables")
@NamedQuery(name="TemplateSeparateurPlage.findAll", query="SELECT t FROM TemplateSeparateurPlage t")
public class TemplateSeparateurPlage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;

	@Column(name="nom_template")
	private String nomTemplate;

	private String separateur;

	public TemplateSeparateurPlage() {
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