package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the destination database table.
 * 
 */
@Entity
@Table(schema="tableref")
@NamedQuery(name="Destination.findAll", query="SELECT d FROM Destination d")
public class Destination implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	

	@ManyToOne
	@JoinColumn(name="id_type",referencedColumnName="id")
	private TypeDestination destination;
	
	
	@OneToMany(mappedBy="dest",cascade={CascadeType.MERGE,CascadeType.REFRESH, CascadeType.REMOVE},orphanRemoval=true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<DetailsDestination> lst_details;
	
	
	public List<DetailsDestination> getLst_details() {
		return lst_details;
	}
	public void setLst_details(List<DetailsDestination> lst_details) {
		this.lst_details = lst_details;
	}
	
	 @OneToMany(mappedBy ="destination")
	 private List<ZonesDestination> zonesdestinations = new ArrayList<ZonesDestination>();
	 
	 
	 
	 
	 public List<ZonesDestination> getZonesdestinations() {
		return zonesdestinations;
	}
	 public void setZonesdestinations(List<ZonesDestination> zonesdestinations) {
		this.zonesdestinations = zonesdestinations;
	}
	
	public TypeDestination getDestination() {
		return destination;
	}
	public void setDestination(TypeDestination destination) {
		this.destination = destination;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="date_debut")
	private Date dateDebut;

	@Temporal(TemporalType.DATE)
	@Column(name="date_fin")
	private Date dateFin;

	@Column(name="date_modif")
	private Timestamp dateModif;

	private String dest;

	
	@Column(name="nom_utilisateur")
	private String nomUtilisateur;

	public Destination() {
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

	public String getDest() {
		return this.dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	

	public String getNomUtilisateur() {
		return this.nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

}