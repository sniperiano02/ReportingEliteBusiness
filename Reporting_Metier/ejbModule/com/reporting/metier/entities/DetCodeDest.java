package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the det_code_dest database table.
 * 
 */
@Entity
@Table(name="det_code_dest",schema="tableref")
@NamedQuery(name="DetCodeDest.findAll", query="SELECT d FROM DetCodeDest d")
public class DetCodeDest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@ManyToOne 
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name="id_destination",referencedColumnName="id",insertable=false,updatable=false)
	private InterDest interdest;
	
	@Column(name="code_dest")
	private String codeDest;

	@Temporal(TemporalType.DATE)
	@Column(name="date_debut")
	private Date dateDebut;

	@Temporal(TemporalType.DATE)
	@Column(name="date_fin")
	private Date dateFin;

	@Column(name="date_modif")
	private Timestamp dateModif;

	@Column(name="id_destination")
	private Integer idDestination;

	@Column(name="nom_utilisateur")
	private String nomUtilisateur;

	public DetCodeDest() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodeDest() {
		return this.codeDest;
	}

	public void setCodeDest(String codeDest) {
		this.codeDest = codeDest;
	}

	public Date getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Timestamp getDateModif() {
		return this.dateModif;
	}

	public void setDateModif(Timestamp dateModif) {
		this.dateModif = dateModif;
	}

	public Integer getIdDestination() {
		return this.idDestination;
	}

	public void setIdDestination(Integer idDestination) {
		this.idDestination = idDestination;
	}

	public String getNomUtilisateur() {
		return this.nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}
	public InterDest getInterdest() {
		return interdest;
	}
	public void setInterdest(InterDest interdest) {
		this.interdest = interdest;
	}

}