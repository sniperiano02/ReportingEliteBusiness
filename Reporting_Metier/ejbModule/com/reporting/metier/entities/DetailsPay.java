package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.sql.Timestamp;


/**
 * The persistent class for the details_pays database table.
 * 
 */
@Entity
@Table(name="details_pays",schema="tableref")
@NamedQuery(name="DetailsPay.findAll", query="SELECT d FROM DetailsPay d")
public class DetailsPay implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="code_pays")
	private String codePays;

	@Column(name="date_modif")
	private Timestamp dateModif;

	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_pays",referencedColumnName="id")
	private Pay pays;
	
	public Pay getPays() {
		return pays;
	}
	public void setPays(Pay pays) {
		this.pays = pays;
	}
	


	@Column(name="nom_utilisateur")
	private String nomUtilisateur;

	public DetailsPay() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodePays() {
		return this.codePays;
	}

	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}

	public Timestamp getDateModif() {
		return this.dateModif;
	}

	public void setDateModif(Timestamp dateModif) {
		this.dateModif = dateModif;
	}

	

	public String getNomUtilisateur() {
		return this.nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

}