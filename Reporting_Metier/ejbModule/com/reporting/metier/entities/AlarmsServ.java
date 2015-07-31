package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.sql.Timestamp;
import java.util.List;
import java.math.BigDecimal;


/**
 * The persistent class for the alarms_serv database table.
 * 
 */
@Entity
@Table(name="alarms_serv",schema="tableref")
@NamedQuery(name="AlarmsServ.findAll", query="SELECT a FROM AlarmsServ a")
public class AlarmsServ implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	
	@ManyToOne 
	@JoinColumn(name="id_serv",referencedColumnName="id_serveur")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Serveur serveur;
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="alarms_utl_srv",schema="tableref",joinColumns={@JoinColumn(name="idalarm", referencedColumnName="id")},inverseJoinColumns={@JoinColumn(name="id_utilisateur", referencedColumnName="u_id")})
	private List<GuiUser> listUsers;
	
	
	
	public List<GuiUser> getListUsers() {
		return listUsers;
	}
	public void setListUsers(List<GuiUser> listUsers) {
		this.listUsers = listUsers;
	}
	public Serveur getServeur() {
		return serveur;
	}
	public void setServeur(Serveur serveur) {
		this.serveur = serveur;
	}

	@Column(name="date_ex")
	private Timestamp dateEx;

	@Column(name="desc_al")
	private String descAl;

	
	private BigDecimal seuil;

	@Column(name="type_al")
	private String typeAl;

	public AlarmsServ() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getDateEx() {
		return this.dateEx;
	}

	public void setDateEx(Timestamp dateEx) {
		this.dateEx = dateEx;
	}

	public String getDescAl() {
		return this.descAl;
	}

	public void setDescAl(String descAl) {
		this.descAl = descAl;
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