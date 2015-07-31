package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the zones database table.
 * 
 */
@Entity
@Table(name="zones",schema="tableref")
@NamedQuery(name="Zone.findAll", query="SELECT z FROM Zone z")
public class Zone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	

	 @OneToMany(mappedBy = "zone",cascade={CascadeType.MERGE,CascadeType.REFRESH, CascadeType.REMOVE},orphanRemoval=true)
	 @LazyCollection(LazyCollectionOption.FALSE)
	 private List<ZonesDestination> zonesdestinations = new ArrayList<ZonesDestination>();
	 
	 
	 
	 public List<ZonesDestination> getZonesdestinations() {
		return zonesdestinations;
	}
	 public void setZonesdestinations(List<ZonesDestination> zonesdestinations) {
		this.zonesdestinations = zonesdestinations;
	}
	 
	 
	@Column(name="code_zones")
	private String codeZones;

	@Column(name="date_modif")
	private Timestamp dateModif;

	@Column(name="date_modification")
	private Timestamp dateModification;

	
	@ManyToOne
	@JoinColumn(name="id_package",referencedColumnName="id")
	private PackageZone packagezone;
	
	public PackageZone getPackagezone() {
		return packagezone;
	}
	public void setPackagezone(PackageZone packagezone) {
		this.packagezone = packagezone;
	}
	
	private String nom;

	@Column(name="nom_utilisateur")
	private String nomUtilisateur;

	public Zone() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodeZones() {
		return this.codeZones;
	}

	public void setCodeZones(String codeZones) {
		this.codeZones = codeZones;
	}

	public Timestamp getDateModif() {
		return this.dateModif;
	}

	public void setDateModif(Timestamp dateModif) {
		this.dateModif = dateModif;
	}

	public Timestamp getDateModification() {
		return this.dateModification;
	}

	public void setDateModification(Timestamp dateModification) {
		this.dateModification = dateModification;
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