package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the pays database table.
 * 
 */
@Entity
@Table(name="pays",schema="tableref")
@NamedQuery(name="Pay.findAll", query="SELECT p FROM Pay p")
public class Pay implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	
	@OneToMany(mappedBy="pays",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<DetailsPay> list_details;
	
	public List<DetailsPay> getList_details() {
		return list_details;
	}
	public void setList_details(List<DetailsPay> list_details) {
		this.list_details = list_details;
	}
	
	@Column(name="date_modif")
	private Timestamp dateModif;

	private String nom;

	@Column(name="nom_utilisateur")
	private String nomUtilisateur;

	public Pay() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getDateModif() {
		return this.dateModif;
	}

	public void setDateModif(Timestamp dateModif) {
		this.dateModif = dateModif;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNomUtilisateur() {
		return this.nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

}