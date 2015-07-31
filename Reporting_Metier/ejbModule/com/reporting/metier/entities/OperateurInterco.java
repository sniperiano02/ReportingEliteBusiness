package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the operateur_interco database table.
 * 
 */
@Entity
@Table(name="operateur_interco",schema="tableref")
@NamedQuery(name="OperateurInterco.findAll", query="SELECT o FROM OperateurInterco o")
public class OperateurInterco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	
	@Column(name="code_operateurs")
	private String codeOperateurs;

	@Column(name="date_modif")
	private Timestamp dateModif;

	
	@ManyToOne
	@JoinColumn(name="id_pays",referencedColumnName="id")
	private Pay pays ;
	
	@ManyToOne
	@JoinColumn(name="id_monnaie",referencedColumnName="id")
	private Monnaie monnaie;
	
	

	@Column(name="nom_utilisateur")
	private String nomUtilisateur;

	private String operateur;

	private String type;

	public OperateurInterco() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeOperateurs() {
		return this.codeOperateurs;
	}

	public void setCodeOperateurs(String codeOperateurs) {
		this.codeOperateurs = codeOperateurs;
	}

	public Timestamp getDateModif() {
		return this.dateModif;
	}

	public void setDateModif(Timestamp dateModif) {
		this.dateModif = dateModif;
	}


	public Pay getPays() {
		return pays;
	}
	public void setPays(Pay pays) {
		this.pays = pays;
	}
	public String getNomUtilisateur() {
		return this.nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getOperateur() {
		return this.operateur;
	}

	public void setOperateur(String operateur) {
		this.operateur = operateur;
	}

	public Monnaie getMonnaie() {
		return monnaie;
	}
	public void setMonnaie(Monnaie monnaie) {
		this.monnaie = monnaie;
	}
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}