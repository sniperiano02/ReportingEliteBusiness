package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the roaming_operator database table.
 * 
 */
@Entity
@Table(name="roaming_operator",schema="tableref")
@NamedQuery(name="RoamingOperator.findAll", query="SELECT r FROM RoamingOperator r")
public class RoamingOperator implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;

	@Column(name="date_modif")
	private Timestamp dateModif;

	@Temporal(TemporalType.DATE)
	@Column(name="date_ouverture")
	private Date dateOuverture;

	
	@ManyToOne
	@JoinColumn(name="id_pays",referencedColumnName="id")
	private Pay pays;
	@Column(name="nom_utilisateur")
	private String nomUtilisateur;

	private String operateur;

	public RoamingOperator() {
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

	public Date getDateOuverture() {
		return this.dateOuverture;
	}

	public void setDateOuverture(Date dateOuverture) {
		this.dateOuverture = dateOuverture;
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

}