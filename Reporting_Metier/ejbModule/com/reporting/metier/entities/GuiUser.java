package com.reporting.metier.entities;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the gui_users database table.
 * 
 */
@Entity
@Table(name="gui_users",schema="tableref")
@NamedQuery(name="GuiUser.findAll", query="SELECT g FROM GuiUser g")
public class GuiUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="u_id")
	private Long uId;

	@Column(name="date_creation")
	private Timestamp dateCreation;

	@Column(name="date_modif")
	private Timestamp dateModif;
	
	@ManyToOne
	@JoinColumn(name="g_id",referencedColumnName="g_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private GuiGroup user_group;
	
	
	
	@ManyToMany(mappedBy="listUsers")
	private List<AlarmsServ> liste_alarmSrv_user;
	
	
	
	@ManyToMany(mappedBy="listUsers_alarmeProc")
	private List<Alertpro> liste_alertePro_user;
	
	
	public List<Alertpro> getListe_alertePro_user() {
		return liste_alertePro_user;
	}
	public void setListe_alertePro_user(List<Alertpro> liste_alertePro_user) {
		this.liste_alertePro_user = liste_alertePro_user;
	}
	public GuiGroup getUser_group() {
		return user_group;
	}
	public void setUser_group(GuiGroup user_group) {
		this.user_group = user_group;
	}



	@Column(name="id_createur")
	private Long idCreateur;

	@Column(name="nom_utilisateur")
	private String nomUtilisateur;

	@Column(name="u_depart")
	private String uDepart;

	@Column(name="u_login")
	private String uLogin;

	@Column(name="u_mail")
	private String uMail;

	@Column(name="u_matricule")
	private String uMatricule;

	@Column(name="u_name")
	private String uName;

	@Column(name="u_pwd")
	private String uPwd;

	public GuiUser() {
	}

	public Long getUId() {
		return this.uId;
	}

	
	public List<AlarmsServ> getListe_alarmSrv_user() {
		return liste_alarmSrv_user;
	}
	public void setListe_alarmSrv_user(List<AlarmsServ> liste_alarmSrv_user) {
		this.liste_alarmSrv_user = liste_alarmSrv_user;
	}
	public void setUId(Long uId) {
		this.uId = uId;
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

	public String getUDepart() {
		return this.uDepart;
	}

	public void setUDepart(String uDepart) {
		this.uDepart = uDepart;
	}

	public String getULogin() {
		return this.uLogin;
	}

	public void setULogin(String uLogin) {
		this.uLogin = uLogin;
	}

	public String getUMail() {
		return this.uMail;
	}

	public void setUMail(String uMail) {
		this.uMail = uMail;
	}

	public String getUMatricule() {
		return this.uMatricule;
	}

	public void setUMatricule(String uMatricule) {
		this.uMatricule = uMatricule;
	}

	public String getUName() {
		return this.uName;
	}

	public void setUName(String uName) {
		this.uName = uName;
	}

	public String getUPwd() {
		return this.uPwd;
	}

	public void setUPwd(String uPwd) {
		this.uPwd = uPwd;
	}

}