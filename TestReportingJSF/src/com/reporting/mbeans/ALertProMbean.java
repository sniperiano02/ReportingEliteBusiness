package com.reporting.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.reporting.metier.entities.Alertpro;
import com.reporting.metier.entities.GuiUser;
import com.reporting.metier.entities.Noeud;
import com.reporting.metier.interfaces.NoeudRemote;
import com.reporting.metier.interfaces.alarmeServeurRemote;
import com.reporting.metier.interfaces.userRemote;


@ManagedBean(name="alarmes_bean")
@ViewScoped
public class ALertProMbean {
	
	
	@EJB
	alarmeServeurRemote alarme_service;
	
	
	@EJB
	userRemote user_service;
	
	@EJB
	NoeudRemote noeud_service;
	
	private List<Alertpro> liste_alarme;
	
	
	private List<GuiUser> liste_users = new ArrayList<>();
	
	private List<GuiUser> liste_selected_user = new ArrayList<>();
	
	public List<GuiUser> getListe_selected_user() {
		return liste_selected_user;
	}
	public void setListe_selected_user(List<GuiUser> liste_selected_user) {
		this.liste_selected_user = liste_selected_user;
	}
	public void setListe_users(List<GuiUser> liste_users) {
		this.liste_users = liste_users;
	}
	public List<GuiUser> getListe_users() {
		return liste_users;
	}
	private Alertpro alertpro;
	private Alertpro alertpro1;
	
	private List<String> liste_noeud;

	
	@PostConstruct
	public void init(){
		liste_noeud= noeud_service.getNoeudCodes();
		liste_alarme=alarme_service.getAllAlertpro();
		liste_users= user_service.getAllUsers();
		alertpro=new Alertpro();
		alertpro1= new Alertpro();
	}
	
	
	public List<String> getListe_noeud() {
		return liste_noeud;
	}
	public void setListe_noeud(List<String> liste_noeud) {
		this.liste_noeud = liste_noeud;
	}
	public void addAlert(){
		alarme_service.addAlertpro(alertpro);
		liste_alarme=alarme_service.getAllAlertpro();
	}
	public void deleteALert(){
		alarme_service.deleteAlertpro(alertpro1);
		liste_alarme=alarme_service.getAllAlertpro();
	}
	
	public void updateAlert(){
		alertpro1.setListUsers_alarmeProc(liste_selected_user);
		alarme_service.updateAlertpro(alertpro1);
		liste_alarme=alarme_service.getAllAlertpro();
	}

	public List<Alertpro> getListe_alarme() {
		return liste_alarme;
	}

	public void setListe_alarme(List<Alertpro> liste_alarme) {
		this.liste_alarme = liste_alarme;
	}

	public Alertpro getAlertpro() {
		return alertpro;
	}

	public void setAlertpro(Alertpro alertpro) {
		this.alertpro = alertpro;
	}

	public Alertpro getAlertpro1() {
		return alertpro1;
	}

	public void setAlertpro1(Alertpro alertpro1) {
		this.alertpro1 = alertpro1;
	}
	
}
