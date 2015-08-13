package com.reporting.mbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.reporting.metier.interfaces.FraudeManagerRemote;


@ManagedBean(name="extraire_cdr")
@ViewScoped
public class ExtraireCDRMbean {

	
	

	@EJB
	private FraudeManagerRemote fraude_service;
	
	
	
	private boolean afficheCDR = false;
	private boolean affichactivation = false;
	private boolean affichrecharge= false;
	private boolean affichtransfert = false;
	
	private String choix_noeud;
	
	
	
	
	private List<Object[]> cdrs ;

	@PostConstruct
	public void init(){
		
		
	}
	public String getChoix_noeud() {
		return choix_noeud;
	}
	public void setChoix_noeud(String choix_noeud) {
		this.choix_noeud = choix_noeud;
	}
	public void Valider(){
		
		
	}
	public List<Object[]> getCdrs() {
		return cdrs;
	}
	public void setCdrs(List<Object[]> cdrs) {
		this.cdrs = cdrs;
	}
}
