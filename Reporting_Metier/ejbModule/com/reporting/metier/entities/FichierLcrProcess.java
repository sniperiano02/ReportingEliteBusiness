package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the fichier_lcr_process database table.
 * 
 */
@Entity
@Table(name="fichier_lcr_process",schema="tableref")
@NamedQuery(name="FichierLcrProcess.findAll", query="SELECT f FROM FichierLcrProcess f")
public class FichierLcrProcess implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="date_effective")
	private Date dateEffective;

	private String etat;

	@Column(name="nom_fichier")
	private String nomFichier;

	
	@ManyToOne
	@JoinColumn(name="operateur",referencedColumnName="id")
	private OperateurInterco operateur;
	

	private String template;

	private String type;

	public FichierLcrProcess() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateEffective() {
		return this.dateEffective;
	}

	public void setDateEffective(Date dateEffective) {
		this.dateEffective = dateEffective;
	}

	public String getEtat() {
		return this.etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getNomFichier() {
		return this.nomFichier;
	}

	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	public OperateurInterco getOperateur() {
		return operateur;
	}
	public void setOperateur(OperateurInterco operateur) {
		this.operateur = operateur;
	}

	public String getTemplate() {
		return this.template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}