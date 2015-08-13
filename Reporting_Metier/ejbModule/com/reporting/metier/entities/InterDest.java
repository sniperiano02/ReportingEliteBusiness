package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inter_dest database table.
 * 
 */
@Entity
@Table(name="inter_dest",schema="tableref")
@NamedQuery(name="InterDest.findAll", query="SELECT i FROM InterDest i")
public class InterDest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(mappedBy="interdest",cascade={CascadeType.MERGE,CascadeType.REFRESH, CascadeType.REMOVE},orphanRemoval=true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<DetCodeDest> list_details;

	@ManyToOne 
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name="id_pays",referencedColumnName="id")
	private Pay pays;
	
	
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_debut")
	private Date dateDebut;

	@Temporal(TemporalType.DATE)
	@Column(name="date_fin")
	private Date dateFin;

	@Column(name="date_modif")
	private Timestamp dateModif;

	@Column(name="group_destination")
	private String groupDestination;


	@Column(name="nom_destination")
	private String nomDestination;

	@Column(name="nom_utilisateur")
	private String nomUtilisateur;

	public InterDest() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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

	public String getGroupDestination() {
		return this.groupDestination;
	}

	public void setGroupDestination(String groupDestination) {
		this.groupDestination = groupDestination;
	}

	
	public String getNomDestination() {
		return this.nomDestination;
	}

	public void setNomDestination(String nomDestination) {
		this.nomDestination = nomDestination;
	}

	public String getNomUtilisateur() {
		return this.nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}
	public Pay getPays() {
		return pays;
	}
	public void setPays(Pay pays) {
		this.pays = pays;
	}
	public List<DetCodeDest> getList_details() {
		return list_details;
	}
	public void setList_details(List<DetCodeDest> list_details) {
		this.list_details = list_details;
	}
	

}