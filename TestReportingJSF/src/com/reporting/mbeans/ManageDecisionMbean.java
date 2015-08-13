package com.reporting.mbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.reporting.metier.entities.DecisionFraude;
import com.reporting.metier.interfaces.FraudeManagerRemote;


@ManagedBean(name="manage_decision")
@ViewScoped
public class ManageDecisionMbean {
	
	@EJB
	FraudeManagerRemote decision_service;
	
	
	
	private List<DecisionFraude> liste_decisions;
	
	private DecisionFraude decision;
	
	public DecisionFraude getDecision() {
		return decision;
	}
	public void setDecision(DecisionFraude decision) {
		this.decision = decision;
	}
	
	private String choix;
	
	public String getChoix() {
		return choix;
	}
	public void setChoix(String choix) {
		this.choix = choix;
	}
	
	
	@PostConstruct
	public void init(){
		liste_decisions= decision_service.getDecisionFraudes(" ");
	decision= new DecisionFraude();	
		
	}
	
	public void affichBlack(){
		liste_decisions= decision_service.getDecisionFraudes("where decision ='"+choix+"'");
	}




public List<DecisionFraude> getListe_decisions() {
	return liste_decisions;
}
public void setListe_decisions(List<DecisionFraude> liste_decisions) {
	this.liste_decisions = liste_decisions;
}
}
