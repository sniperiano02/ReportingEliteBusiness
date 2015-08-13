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
 * The persistent class for the tarifs_roa_in database table.
 * 
 */
@Entity
@Table(name="tarifs_roa_in",schema="tableref")
@NamedQuery(name="TarifsRoaIn.findAll", query="SELECT t FROM TarifsRoaIn t")
public class TarifsRoaIn implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="code_service")
	private String codeService;

	@Temporal(TemporalType.DATE)
	@Column(name="date_debut_validite")
	private Date dateDebutValidite;

	@Column(name="date_modif")
	private Timestamp dateModif;

	@ManyToOne
	 @NotFound(
	            action = NotFoundAction.IGNORE)
	@JoinColumn(name="id_zone",referencedColumnName="id")
	private Zone zone;
	
	@ManyToOne
	 @NotFound(
	            action = NotFoundAction.IGNORE)
	@JoinColumn(name="id_package_zone",referencedColumnName="id")
	private PackageZone packZone;
	
	@ManyToOne
	 @NotFound(
	            action = NotFoundAction.IGNORE)
	@JoinColumn(name="id_package_jour",referencedColumnName="id")
	private PackageJour packJour;
	
	@ManyToOne
	@JoinColumn(name="id_interval",referencedColumnName="id")
	private IntervalHeure intervalle;

	@ManyToOne
	@JoinColumn(name="id_group_roaming",referencedColumnName="id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private PackageRoaming group_roaming;
	
	
	@ManyToOne
	@JoinColumn(name="id_monnaie",referencedColumnName="id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Monnaie monnaie ;
	

	
	
	

	@Column(name="nom_utilisateur")
	private String nomUtilisateur;

	@Column(name="palier_taxation")
	private BigDecimal palierTaxation;

	@Column(name="prix_tarif")
	private BigDecimal prixTarif;

	@ManyToOne
	 @NotFound(
	            action = NotFoundAction.IGNORE)
	@JoinColumn(name="type_palier",referencedColumnName="type_palier")
	private TypePalier Paliertype;

	
	public TypePalier getPaliertype() {
		return Paliertype;
	}
	public void setPaliertype(TypePalier paliertype) {
		Paliertype = paliertype;
	}

	public TarifsRoaIn() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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




	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
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

	public PackageRoaming getGroup_roaming() {
		return group_roaming;
	}

	public void setGroup_roaming(PackageRoaming group_roaming) {
		this.group_roaming = group_roaming;
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