package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.sql.Timestamp;
import java.util.List;
import java.math.BigDecimal;


/**
 * The persistent class for the alertpros database table.
 * 
 */
@Entity
@Table(name="alertpros",schema="tableref")
@NamedQuery(name="Alertpro.findAll", query="SELECT a FROM Alertpro a")
public class Alertpro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="al_act")
	private BigDecimal alAct;

	private BigDecimal flag;

	@Column(name="last_ex")
	private Timestamp lastEx;

	@Column(name="last_file")
	private String lastFile;

	private String noeud;

	@Column(name="nom_alert")
	private String nomAlert;

	@Column(name="rep_alert")
	private BigDecimal repAlert;

	private BigDecimal seuil;

	@Column(name="type_al")
	private String typeAl;

	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="alarms_utl",schema="tableref",joinColumns={@JoinColumn(name="idalarm", referencedColumnName="id")},inverseJoinColumns={@JoinColumn(name="id_utilisateur", referencedColumnName="u_id")})
	private List<GuiUser> listUsers_alarmeProc;
	
	public List<GuiUser> getListUsers_alarmeProc() {
		return listUsers_alarmeProc;
	}
	public void setListUsers_alarmeProc(List<GuiUser> listUsers_alarmeProc) {
		this.listUsers_alarmeProc = listUsers_alarmeProc;
	}
	public Alertpro() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getAlAct() {
		return this.alAct;
	}

	public void setAlAct(BigDecimal alAct) {
		this.alAct = alAct;
	}

	public BigDecimal getFlag() {
		return this.flag;
	}

	public void setFlag(BigDecimal flag) {
		this.flag = flag;
	}

	public Timestamp getLastEx() {
		return this.lastEx;
	}

	public void setLastEx(Timestamp lastEx) {
		this.lastEx = lastEx;
	}

	public String getLastFile() {
		return this.lastFile;
	}

	public void setLastFile(String lastFile) {
		this.lastFile = lastFile;
	}

	public String getNoeud() {
		return this.noeud;
	}

	public void setNoeud(String noeud) {
		this.noeud = noeud;
	}

	public String getNomAlert() {
		return this.nomAlert;
	}

	public void setNomAlert(String nomAlert) {
		this.nomAlert = nomAlert;
	}

	public BigDecimal getRepAlert() {
		return this.repAlert;
	}

	public void setRepAlert(BigDecimal repAlert) {
		this.repAlert = repAlert;
	}

	public BigDecimal getSeuil() {
		return this.seuil;
	}

	public void setSeuil(BigDecimal seuil) {
		this.seuil = seuil;
	}

	public String getTypeAl() {
		return this.typeAl;
	}

	public void setTypeAl(String typeAl) {
		this.typeAl = typeAl;
	}

}