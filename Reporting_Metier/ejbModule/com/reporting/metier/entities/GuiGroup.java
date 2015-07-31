package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the gui_groups database table.
 * 
 */
@Entity
@Table(name="gui_groups",schema="tableref")
@NamedQuery(name="GuiGroup.findAll", query="SELECT g FROM GuiGroup g")
public class GuiGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="g_id")
	private Long gId;
	
	
	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(name="tab_groups",schema="tableref")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<GuiTabsGroup> tab_groups;

	
	public List<GuiTabsGroup> getTab_groups() {
		return tab_groups;
	}
	public void setTab_groups(List<GuiTabsGroup> tab_groups) {
		this.tab_groups = tab_groups;
	}
	@Column(name="date_creation")
	private Timestamp dateCreation;

	@Column(name="date_modif")
	private Timestamp dateModif;

	@Column(name="g_description")
	private String gDescription;

	@Column(name="g_name")
	private String gName;

	@Column(name="id_createur")
	private Long idCreateur;

	@Column(name="nom_utilisateur")
	private String nomUtilisateur;

	public GuiGroup() {
	}

	public Long getGId() {
		return this.gId;
	}

	public void setGId(Long gId) {
		this.gId = gId;
	}

	public Timestamp getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Timestamp dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Timestamp getDateModif() {
		return this.dateModif;
	}

	public void setDateModif(Timestamp dateModif) {
		this.dateModif = dateModif;
	}

	public String getGDescription() {
		return this.gDescription;
	}

	public void setGDescription(String gDescription) {
		this.gDescription = gDescription;
	}

	public String getGName() {
		return this.gName;
	}

	public void setGName(String gName) {
		this.gName = gName;
	}

	public Long getIdCreateur() {
		return this.idCreateur;
	}

	public void setIdCreateur(Long idCreateur) {
		this.idCreateur = idCreateur;
	}

	public String getNomUtilisateur() {
		return this.nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

}