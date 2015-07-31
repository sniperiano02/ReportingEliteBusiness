package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the template database table.
 * 
 */
@Entity
@Table(schema="tables")
@NamedQuery(name="Template.findAll", query="SELECT t FROM Template t")
public class Template implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;

	@Column(name="chiffre_compose")
	private String chiffreCompose;

	@Column(name="code_pays")
	private String codePays;

	@Temporal(TemporalType.DATE)
	@Column(name="date_creation")
	private Date dateCreation;

	@Column(name="date_effective")
	private String dateEffective;

	@Column(name="date_indique")
	private String dateIndique;

	private String destination;

	@Column(name="format_date")
	private String formatDate;

	@Column(name="format_tarif")
	private String formatTarif;

	@Column(name="nb_feuille")
	private Long nbFeuille;

	@Column(name="nom_template")
	private String nomTemplate;

	@Column(name="num_code_dest")
	private Long numCodeDest;

	@Column(name="num_code_pays")
	private Long numCodePays;

	@Column(name="num_date_effective")
	private Long numDateEffective;

	@Column(name="num_destination")
	private Long numDestination;

	@Column(name="num_qualite")
	private Long numQualite;

	@Column(name="num_tarif")
	private Long numTarif;

	
	@OneToMany(mappedBy="template",cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<TemplateFeuille> lsttemplateFeuille;
	
	
	@ManyToOne
	@JoinColumn(name="operateur",referencedColumnName="id")
	private OperateurInterco operateur;
	
	  @OneToOne
	  @JoinColumn(name="temp_date_indique_id")
	  private TemplateDateIndique tempDateIndique;
	  
	  
	  public TemplateDateIndique getTempDateIndique() {
		return tempDateIndique;
	}
	  
	  public void setTempDateIndique(TemplateDateIndique tempDateIndique) {
		this.tempDateIndique = tempDateIndique;
	}
	
	public List<TemplateFeuille> getLsttemplateFeuille() {
		return lsttemplateFeuille;
	}
	public void setLsttemplateFeuille(List<TemplateFeuille> lsttemplateFeuille) {
		this.lsttemplateFeuille = lsttemplateFeuille;
	}
	

	@Column(name="separateur_plage")
	private String separateurPlage;

	public Template() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getChiffreCompose() {
		return this.chiffreCompose;
	}

	public void setChiffreCompose(String chiffreCompose) {
		this.chiffreCompose = chiffreCompose;
	}

	public String getCodePays() {
		return this.codePays;
	}

	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}

	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public String getDateEffective() {
		return this.dateEffective;
	}

	public void setDateEffective(String dateEffective) {
		this.dateEffective = dateEffective;
	}

	public String getDateIndique() {
		return this.dateIndique;
	}

	public void setDateIndique(String dateIndique) {
		this.dateIndique = dateIndique;
	}

	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getFormatDate() {
		return this.formatDate;
	}

	public void setFormatDate(String formatDate) {
		this.formatDate = formatDate;
	}

	public String getFormatTarif() {
		return this.formatTarif;
	}

	public void setFormatTarif(String formatTarif) {
		this.formatTarif = formatTarif;
	}

	public Long getNbFeuille() {
		return this.nbFeuille;
	}

	public void setNbFeuille(Long nbFeuille) {
		this.nbFeuille = nbFeuille;
	}

	public String getNomTemplate() {
		return this.nomTemplate;
	}

	public void setNomTemplate(String nomTemplate) {
		this.nomTemplate = nomTemplate;
	}

	public Long getNumCodeDest() {
		return this.numCodeDest;
	}

	public void setNumCodeDest(Long numCodeDest) {
		this.numCodeDest = numCodeDest;
	}

	public Long getNumCodePays() {
		return this.numCodePays;
	}

	public void setNumCodePays(Long numCodePays) {
		this.numCodePays = numCodePays;
	}

	public Long getNumDateEffective() {
		return this.numDateEffective;
	}

	public void setNumDateEffective(Long numDateEffective) {
		this.numDateEffective = numDateEffective;
	}

	public Long getNumDestination() {
		return this.numDestination;
	}

	public void setNumDestination(Long numDestination) {
		this.numDestination = numDestination;
	}

	public Long getNumQualite() {
		return this.numQualite;
	}

	public void setNumQualite(Long numQualite) {
		this.numQualite = numQualite;
	}

	public Long getNumTarif() {
		return this.numTarif;
	}

	public void setNumTarif(Long numTarif) {
		this.numTarif = numTarif;
	}

	public OperateurInterco getOperateur() {
		return operateur;
	}
	public void setOperateur(OperateurInterco operateur) {
		this.operateur = operateur;
	}

	public String getSeparateurPlage() {
		return this.separateurPlage;
	}

	public void setSeparateurPlage(String separateurPlage) {
		this.separateurPlage = separateurPlage;
	}

}