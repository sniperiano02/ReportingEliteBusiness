package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the truncks database table.
 * 
 */
@Entity
@Table(name="truncks",schema="tableref")
@NamedQuery(name="Trunck.findAll", query="SELECT t FROM Trunck t")
public class Trunck implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@ManyToOne 
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name="id_operateur",referencedColumnName="id",insertable=false,updatable=false)
	private OperateurInterco operateur;
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_debut")
	private Date dateDebut;

	@Temporal(TemporalType.DATE)
	@Column(name="date_fin")
	private Date dateFin;

	@Column(name="date_modif")
	private Timestamp dateModif;

	@Column(name="id_operateur")
	private Integer idOperateur;

	@Column(name="id_trunck")
	private String idTrunck;

	private String international;

	private String noeud;

	private String nom;

	@Column(name="nom_utilisateur")
	private String nomUtilisateur;

	public Trunck() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getIdOperateur() {
		return this.idOperateur;
	}

	public void setIdOperateur(Integer idOperateur) {
		this.idOperateur = idOperateur;
	}

	public String getIdTrunck() {
		return this.idTrunck;
	}

	public void setIdTrunck(String idTrunck) {
		this.idTrunck = idTrunck;
	}

	public String getInternational() {
		return this.international;
	}

	public void setInternational(String international) {
		this.international = international;
	}

	public String getNoeud() {
		return this.noeud;
	}

	public void setNoeud(String noeud) {
		this.noeud = noeud;
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
	public OperateurInterco getOperateur() {
		return operateur;
	}
	public void setOperateur(OperateurInterco operateur) {
		this.operateur = operateur;
	}

}