package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the jours_semaine database table.
 * 
 */
@Entity
@Table(name="jours_semaine",schema="tableref")
@NamedQuery(name="JoursSemaine.findAll", query="SELECT j FROM JoursSemaine j")
public class JoursSemaine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;

	
	private String journee;

	public JoursSemaine() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="id_package",referencedColumnName="id")
	private PackageJour packagejour;
	
	
	public PackageJour getPackagejour() {
		return packagejour;
	}
	public void setPackagejour(PackageJour packagejour) {
		this.packagejour = packagejour;
	}

	
	public String getJournee() {
		return this.journee;
	}

	public void setJournee(String journee) {
		this.journee = journee;
	}

}