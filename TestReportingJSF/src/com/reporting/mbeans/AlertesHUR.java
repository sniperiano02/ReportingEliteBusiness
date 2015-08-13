package com.reporting.mbeans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.reporting.metier.entities.DecisionFraude;
import com.reporting.metier.entities.ReglesFraude;
import com.reporting.metier.interfaces.FraudeManagerRemote;


@ManagedBean(name="alerte_hur")
@ViewScoped
public class AlertesHUR {
	
	

	@EJB
	private FraudeManagerRemote fraude_service;
	
	private Date date_alerte;
	
	
	public Date getDate_alerte() {
		return date_alerte;
	}
	public void setDate_alerte(Date date_alerte) {
		this.date_alerte = date_alerte;
	}
	
	private boolean afficheCDR = false;
	private boolean affichactivation = false;
	private boolean affichrecharge= false;
	private boolean affichtransfert = false;
	
	
	
	
	private List<Object[]> cdrs ;
	private List<Object[]> cdrs_in_activations;
	private List<Object[]> cdrs_in_recharge;
	private List<Object[]> cdrs_in_transfert;
	
	private Object[] identities;
	private Object[] selected_alerte;
	public Object[] getIdentities() {
		return identities;
	}
	public void setIdentities(Object[] identities) {
		this.identities = identities;
	}
	
	private List<Object[]> liste_parametres = new ArrayList<>();
	
	public Object[] getSelected_alerte() {
		return selected_alerte;
	}
	public void setSelected_alerte(Object[] selected_alerte) {
		this.selected_alerte = selected_alerte;
	}
	
	private List<ReglesFraude> liste_regles;
	
	
	private List<Object[]> listeAlerte = new ArrayList<>();
	
	private ReglesFraude selected_regle;
	
	
	@PostConstruct
	public void init(){
		selected_regle= new ReglesFraude();
		
		liste_regles= fraude_service.getAllFraudes(" where type='HUR' OR type ='Les Deux'");
	}
	
	public void BlackList(){
		DecisionFraude df = new DecisionFraude();
		df.setDecision("B");
		df.setMsisdn(selected_alerte[1].toString());
		
		fraude_service.decision(df,selected_alerte[1].toString());
		
		listeAlerte = fraude_service.getAlerteFraudeByRegle(selected_regle);
	}
	public void WhiteList(){
		DecisionFraude df = new DecisionFraude();
		df.setDecision("W");
		df.setMsisdn(selected_alerte[1].toString());
		
		fraude_service.decision(df,selected_alerte[1].toString());
		listeAlerte = fraude_service.getAlerteFraudeByRegle(selected_regle);}
	public void Supprimer(){
		DecisionFraude df = new DecisionFraude();
		df.setDecision("S");
		df.setMsisdn(selected_alerte[1].toString());
		
		fraude_service.decision(df,selected_alerte[1].toString());
		listeAlerte = fraude_service.getAlerteFraudeByRegle(selected_regle);}
	public void Desactiver(){
		DecisionFraude df = new DecisionFraude();
		df.setDecision("D");
		df.setMsisdn(selected_alerte[1].toString());
		
		fraude_service.decision(df,selected_alerte[1].toString());
		listeAlerte = fraude_service.getAlerteFraudeByRegle(selected_regle);
	}
	
	
	public void selectRegle(){
	    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String today = formatter.format(this.getDate_alerte());
        System.out.println("Today : " + today);
		listeAlerte = fraude_service.getAlerteHURByRegle(selected_regle,today);
	}
	
	public void loadIdentitéCdr(){
		switch (selected_regle.getFlux().getNom()) {
		case "msc":
			cdrs=fraude_service.getCDRByTypeRegle("msc"+selected_alerte[4].toString().substring(0, 6),"where appelant = '"+selected_alerte[1].toString().substring(3, selected_alerte[1].toString().length())+"");
			break;
		case "IN":
			cdrs=fraude_service.getCDRByTypeRegle("in"+selected_alerte[4].toString().substring(0, 6),"where appelant = '"+selected_alerte[1].toString().substring(3, selected_alerte[1].toString().length())+"");
			break;
		case "sys fact":
			cdrs=fraude_service.getCDRByTypeRegle("sysfact"+selected_alerte[4].toString().substring(0, 6),"where appelant = '"+selected_alerte[1].toString().substring(3, selected_alerte[1].toString().length())+"");
			break;
		case "TAP IN":
			cdrs=fraude_service.getCDRByTypeRegle("tapin"+selected_alerte[4].toString().substring(0, 6),"where appelant = '"+selected_alerte[1].toString().substring(3, selected_alerte[1].toString().length())+"");
			break;
		case "TAP OUT":
			cdrs=fraude_service.getCDRByTypeRegle("tapout"+selected_alerte[4].toString().substring(0, 6),"where appelant = '"+selected_alerte[1].toString().substring(3, selected_alerte[1].toString().length())+"");
			break;

		default:
			break;
		}
		if(cdrs!=null)
			afficheCDR=true;
		
		cdrs_in_activations= fraude_service.getCDRBActivatonyTypeRegle(selected_alerte[4].toString().substring(0, 6),"where appelant = '"+selected_alerte[1].toString().substring(3, selected_alerte[1].toString().length())+"");
		if(cdrs_in_activations!=null)
			affichactivation = true;
		
		cdrs_in_recharge=fraude_service.getCDRRechargeByTypeRegle(selected_alerte[4].toString().substring(0, 6),"where appelant = '"+selected_alerte[1].toString().substring(3, selected_alerte[1].toString().length())+"");
		if(cdrs_in_recharge!=null)
			affichrecharge = true;
		
		
		cdrs_in_transfert= fraude_service.getCDRTransfertByTypeRegle(selected_alerte[4].toString().substring(0, 6),"where appelant = '"+selected_alerte[1].toString().substring(3, selected_alerte[1].toString().length())+"");
		if(cdrs_in_transfert!=null)
			affichtransfert = true;
		liste_parametres= fraude_service.getParametres(selected_regle, selected_alerte[1].toString());
		
		identities = fraude_service.getDecisions(selected_alerte[1].toString());
	
	}
	
	public List<Object[]> getListeAlerte() {
		return listeAlerte;
	}
	public void setListeAlerte(List<Object[]> listeAlerte) {
		this.listeAlerte = listeAlerte;
	}
	public ReglesFraude getSelected_regle() {
		return selected_regle;
	}
	public void setSelected_regle(ReglesFraude selected_regle) {
		this.selected_regle = selected_regle;
	}
	public List<ReglesFraude> getListe_regles() {
		return liste_regles;
	}
	public void setListe_regles(List<ReglesFraude> liste_regles) {
		this.liste_regles = liste_regles;
	}
	public List<Object[]> getListe_parametres() {
		return liste_parametres;
	}
	public void setListe_parametres(List<Object[]> liste_parametres) {
		this.liste_parametres = liste_parametres;
	}
	public boolean isAfficheCDR() {
		return afficheCDR;
	}
	public void setAfficheCDR(boolean afficheCDR) {
		this.afficheCDR = afficheCDR;
	}
	public boolean isAffichactivation() {
		return affichactivation;
	}
	public void setAffichactivation(boolean affichactivation) {
		this.affichactivation = affichactivation;
	}
	public boolean isAffichrecharge() {
		return affichrecharge;
	}
	public void setAffichrecharge(boolean affichrecharge) {
		this.affichrecharge = affichrecharge;
	}
	public boolean isAffichtransfert() {
		return affichtransfert;
	}
	public void setAffichtransfert(boolean affichtransfert) {
		this.affichtransfert = affichtransfert;
	}
	public List<Object[]> getCdrs() {
		return cdrs;
	}
	public void setCdrs(List<Object[]> cdrs) {
		this.cdrs = cdrs;
	}
	public List<Object[]> getCdrs_in_activations() {
		return cdrs_in_activations;
	}
	public void setCdrs_in_activations(List<Object[]> cdrs_in_activations) {
		this.cdrs_in_activations = cdrs_in_activations;
	}
	public List<Object[]> getCdrs_in_recharge() {
		return cdrs_in_recharge;
	}
	public void setCdrs_in_recharge(List<Object[]> cdrs_in_recharge) {
		this.cdrs_in_recharge = cdrs_in_recharge;
	}
	public List<Object[]> getCdrs_in_transfert() {
		return cdrs_in_transfert;
	}
	public void setCdrs_in_transfert(List<Object[]> cdrs_in_transfert) {
		this.cdrs_in_transfert = cdrs_in_transfert;
	}
	
	
	

}
