package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the package_roaming database table.
 * 
 */
@Entity
@Table(name="package_roaming",schema="tableref")
@NamedQuery(name="PackageRoaming.findAll", query="SELECT p FROM PackageRoaming p")
public class PackageRoaming implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String code;
	
	
	@OneToOne(mappedBy="pack_roaming",cascade = CascadeType.ALL)
	private RoaminginParameter roaminginParameter;

	@Column(name="date_modif")
	private Timestamp dateModif;

	private String nom;

	@Column(name="nom_utlisateur")
	private String nomUtlisateur;

	public PackageRoaming() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getNomUtlisateur() {
		return this.nomUtlisateur;
	}

	public void setNomUtlisateur(String nomUtlisateur) {
		this.nomUtlisateur = nomUtlisateur;
	}

}