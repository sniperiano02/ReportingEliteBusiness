package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the cell database table.
 * 
 */
@Entity
@Table(schema="tableref")
@NamedQuery(name="Cell.findAll", query="SELECT c FROM Cell c")
public class Cell implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idcell;

	private float altitude;

	private float angle;

	private float azimuth;

	@Column(name="date_modif")
	private Timestamp dateModif;

	private float latitude;

	private String nom;

	@Column(name="nom_bsc")
	private String nomBsc;

	@Column(name="nom_msc")
	private String nomMsc;

	@Column(name="nom_utilisateur")
	private String nomUtilisateur;

	public Cell() {
	}

	public String getIdcell() {
		return this.idcell;
	}

	public void setIdcell(String idcell) {
		this.idcell = idcell;
	}

	public float getAltitude() {
		return this.altitude;
	}

	public void setAltitude(float altitude) {
		this.altitude = altitude;
	}

	public float getAngle() {
		return this.angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public float getAzimuth() {
		return this.azimuth;
	}

	public void setAzimuth(float azimuth) {
		this.azimuth = azimuth;
	}

	public Timestamp getDateModif() {
		return this.dateModif;
	}

	public void setDateModif(Timestamp dateModif) {
		this.dateModif = dateModif;
	}

	public float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNomBsc() {
		return this.nomBsc;
	}

	public void setNomBsc(String nomBsc) {
		this.nomBsc = nomBsc;
	}

	public String getNomMsc() {
		return this.nomMsc;
	}

	public void setNomMsc(String nomMsc) {
		this.nomMsc = nomMsc;
	}

	public String getNomUtilisateur() {
		return this.nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

}