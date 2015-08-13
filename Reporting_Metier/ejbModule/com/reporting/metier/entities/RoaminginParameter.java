package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the roamingin_parameter database table.
 * 
 */
@Entity
@Table(name="roamingin_parameter",schema="tableref")
@NamedQuery(name="RoaminginParameter.findAll", query="SELECT r FROM RoaminginParameter r")
public class RoaminginParameter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="date_debut_validite")
	private Date dateDebutValidite;

	private String etat;

	
	
	
	@OneToOne
	@JoinColumn(name="id_group_roaming",referencedColumnName="id")
	private PackageRoaming pack_roaming;
	
	public PackageRoaming getPack_roaming() {
		return pack_roaming;
	}
	public void setPack_roaming(PackageRoaming pack_roaming) {
		this.pack_roaming = pack_roaming;
	}
	
	public RoaminginParameter() {
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

	public String getEtat() {
		return this.etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	
}