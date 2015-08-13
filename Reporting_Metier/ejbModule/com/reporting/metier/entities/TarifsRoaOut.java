package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tarifs_roa_out database table.
 * 
 */
@Entity
@Table(name="tarifs_roa_out",schema="tableref")
@NamedQuery(name="TarifsRoaOut.findAll", query="SELECT t FROM TarifsRoaOut t")
public class TarifsRoaOut implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	
	
	@ManyToOne
	@JoinColumn(name="code_plan_tarif",referencedColumnName="code_plan_tarifaire")
	private PlanTarifaire plan;
	
	@ManyToOne
	 @NotFound(
	            action = NotFoundAction.IGNORE)
	@JoinColumn(name="code_zone",referencedColumnName="id")
	private Zone zone;
	
	@ManyToOne
	@JoinColumn(name="id_package_zone_roa",referencedColumnName="id")
	private PackageZone pack_zone_roa;
	
	@ManyToOne
	@JoinColumn(name="id_package_zone",referencedColumnName="id")
	private PackageZone pack_zone;
	
	@ManyToOne
	 @NotFound(
	            action = NotFoundAction.IGNORE)
	@JoinColumn(name="type_palier",referencedColumnName="type_palier")
	private TypePalier Paliertype;
	
	@ManyToOne
	 @NotFound(
	            action = NotFoundAction.IGNORE)
	@JoinColumn(name="code_zone_roa",referencedColumnName="id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Zone zone_roa;
	

	@Column(name="code_service")
	private String codeService;



	@Temporal(TemporalType.DATE)
	@Column(name="date_debut_validite")
	private Date dateDebutValidite;

	@Column(name="date_modif")
	private Timestamp dateModif;



	@ManyToOne
	@JoinColumn(name="id_package_jour",referencedColumnName="id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private PackageJour packJour;
	
	@ManyToOne
	@JoinColumn(name="id_interval",referencedColumnName="id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private IntervalHeure intervalle;

	@Column(name="nom_utilisateur")
	private String nomUtilisateur;

	@Column(name="palier_taxation")
	private BigDecimal palierTaxation;

	@Column(name="prix_tarif")
	private BigDecimal prixTarif;

	

	public TarifsRoaOut() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PlanTarifaire getPlan() {
		return plan;
	}
	public void setPlan(PlanTarifaire plan) {
		this.plan = plan;
	}

	public String getCodeService() {
		return this.codeService;
	}

	public void setCodeService(String codeService) {
		this.codeService = codeService;
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

public IntervalHeure getIntervalle() {
	return intervalle;
}
public void setIntervalle(IntervalHeure intervalle) {
	this.intervalle = intervalle;
}

	

	public Zone getZone() {
	return zone;
}

public void setZone(Zone zone) {
	this.zone = zone;
}

public PackageZone getPack_zone_roa() {
	return pack_zone_roa;
}

public void setPack_zone_roa(PackageZone pack_zone_roa) {
	this.pack_zone_roa = pack_zone_roa;
}

public PackageZone getPack_zone() {
	return pack_zone;
}

public void setPack_zone(PackageZone pack_zone) {
	this.pack_zone = pack_zone;
}

public TypePalier getPaliertype() {
	return Paliertype;
}

public void setPaliertype(TypePalier paliertype) {
	Paliertype = paliertype;
}

public Zone getZone_roa() {
	return zone_roa;
}

public void setZone_roa(Zone zone_roa) {
	this.zone_roa = zone_roa;
}

public PackageJour getPackJour() {
	return packJour;
}

public void setPackJour(PackageJour packJour) {
	this.packJour = packJour;
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

	
}