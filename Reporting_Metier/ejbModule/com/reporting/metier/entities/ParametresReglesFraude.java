package com.reporting.metier.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the parametres_regles_fraudes database table.
 * 
 */
@Entity
@Table(name="parametres_regles_fraudes",schema="tableref")
@NamedQuery(name="ParametresReglesFraude.findAll", query="SELECT p FROM ParametresReglesFraude p")
public class ParametresReglesFraude implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="date_modif")
	private Timestamp dateModif;

	
	@ManyToOne
	@JoinColumn(name="id_parametre",referencedColumnName="id")
	private ParametresFraude parametreFraude;
	
	
	@ManyToOne
	@JoinColumn(name="id_regle",referencedColumnName="id")
	private ReglesFraude regle;
	
	public ReglesFraude getRegle() {
		return regle;
	}
	public void setRegle(ReglesFraude regle) {
		this.regle = regle;
	}
	


	@Column(name="nom_utilisateur")
	private String nomUtilisateur;

	private Integer vegal;

	private Integer vmax;

	private Integer vmin;

	public ParametresReglesFraude() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getDateModif() {
		return this.dateModif;
	}

	public void setDateModif(Timestamp dateModif) {
		this.dateModif = dateModif;
	}

	public ParametresFraude getParametreFraude() {
		return parametreFraude;
	}
	public void setParametreFraude(ParametresFraude parametreFraude) {
		this.parametreFraude = parametreFraude;
	}
	

	public String getNomUtilisateur() {
		return this.nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public Integer getVegal() {
		return this.vegal;
	}

	public void setVegal(Integer vegal) {
		this.vegal = vegal;
	}

	public Integer getVmax() {
		return this.vmax;
	}

	public void setVmax(Integer vmax) {
		this.vmax = vmax;
	}

	public Integer getVmin() {
		return this.vmin;
	}

	public void setVmin(Integer vmin) {
		this.vmin = vmin;
	}

}