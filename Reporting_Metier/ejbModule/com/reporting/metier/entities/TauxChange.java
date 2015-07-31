package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the taux_change database table.
 * 
 */
@Entity
@Table(name="taux_change",schema="tableref")
@NamedQuery(name="TauxChange.findAll", query="SELECT t FROM TauxChange t")
public class TauxChange implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="date_debut_validite")
	private Date dateDebutValidite;

	@Column(name="date_modif")
	private Timestamp dateModif;

	@ManyToOne
	@JoinColumn(name="id_monnaie",referencedColumnName="id")
	private Monnaie monnaie;
	
	

	@Column(name="nom_utilisateur")
	private String nomUtilisateur;

	@Column(name="taux_change")
	private double tauxChange;

	public TauxChange() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateDebutValidite() {
		return this.dateDebutValidite;
	}

	public void setDateDebutValidite(Date dateDebutValidite) {
		this.dateDebutValidite = dateDebutValidite;
	}

	public Timestamp getDateModif() {
		return this.dateModif;
	}

	public void setDateModif(Timestamp dateModif) {
		this.dateModif = dateModif;
	}

	public Monnaie getMonnaie() {
		return monnaie;
	}
	public void setMonnaie(Monnaie monnaie) {
		this.monnaie = monnaie;
	}
	public String getNomUtilisateur() {
		return this.nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public double getTauxChange() {
		return this.tauxChange;
	}

	public void setTauxChange(double tauxChange) {
		this.tauxChange = tauxChange;
	}

}