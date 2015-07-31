package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tarifs database table.
 * 
 */
@Entity
@Table(name="tarifs",schema="tableref")
@NamedQuery(name="Tarif.findAll", query="SELECT t FROM Tarif t")
public class Tarif implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	

	
	@ManyToOne
	@JoinColumn(name="code_plan_tarif",referencedColumnName="code_plan_tarifaire")
	@LazyCollection(LazyCollectionOption.FALSE)
	private PlanTarifaire plan;
	@ManyToOne
	@JoinColumn(name="type_palier",referencedColumnName="type_palier")
	@LazyCollection(LazyCollectionOption.FALSE)
	private TypePalier Paliertype;
	
	@ManyToOne
	@JoinColumn(name="code_service",referencedColumnName="code_service")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Service service;
	@ManyToOne
	@JoinColumn(name="id_zone",referencedColumnName="id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Zone zone;
	
	@ManyToOne
	@JoinColumn(name="id_package_zone",referencedColumnName="id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private PackageZone packZone;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="id_package_jour",referencedColumnName="id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private PackageJour packJour;
	
	@ManyToOne
	@JoinColumn(name="id_interval",referencedColumnName="id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private IntervalHeure intervalle;

	@Temporal(TemporalType.DATE)
	@Column(name="date_debut_validite")
	private Date dateDebutValidite;

	@Column(name="date_modif")
	private Timestamp dateModif;


	@Column(name="nom_utilisateur")
	private String nomUtilisateur;

	@Column(name="palier_taxation")
	private BigDecimal palierTaxation;

	@Column(name="prix_tarif")
	private BigDecimal prixTarif;


	public Tarif() {
	}
	
	
	public void setZone(Zone zone) {
		this.zone = zone;
	}
	public Zone getZone() {
		return zone;
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
	
	public String getNomUtilisateur() {
		return this.nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public BigDecimal getPalierTaxation() {
		return this.palierTaxation;
	}

	public void setPalierTaxation(BigDecimal palierTaxation) {
		this.palierTaxation = palierTaxation;
	}

	public BigDecimal getPrixTarif() {
		return this.prixTarif;
	}

	public void setPrixTarif(BigDecimal prixTarif) {
		this.prixTarif = prixTarif;
	}



	public PlanTarifaire getPlan() {
		return plan;
	}


	public void setPlan(PlanTarifaire plan) {
		this.plan = plan;
	}


	public TypePalier getPaliertype() {
		return Paliertype;
	}


	public void setPaliertype(TypePalier paliertype) {
		Paliertype = paliertype;
	}


	public Service getService() {
		return service;
	}


	public void setService(Service service) {
		this.service = service;
	}


	public PackageZone getPackZone() {
		return packZone;
	}


	public void setPackZone(PackageZone packZone) {
		this.packZone = packZone;
	}


	public PackageJour getPackJour() {
		return packJour;
	}


	public void setPackJour(PackageJour packJour) {
		this.packJour = packJour;
	}
	public IntervalHeure getIntervalle() {
		return intervalle;
	}
	public void setIntervalle(IntervalHeure intervalle) {
		this.intervalle = intervalle;
	}

}