package com.reporting.mbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.reporting.metier.entities.Monnaie;
import com.reporting.metier.entities.TauxChange;
import com.reporting.metier.interfaces.TauxChangeRemote;


@ManagedBean(name="taux_manage")
@ViewScoped
public class TauxChangeMbean {

	
	@EJB
	private TauxChangeRemote taux_change_service;
	
	private TauxChange taux;
	
	private TauxChange taux1;
	
	private List<TauxChange> liste_taux;
	private List<Monnaie> liste_monnaie;
	
	public List<Monnaie> getListe_monnaie() {
		return liste_monnaie;
	}
	public void setListe_monnaie(List<Monnaie> liste_monnaie) {
		this.liste_monnaie = liste_monnaie;
	}
	
	
	
	
	@PostConstruct
	public void init(){
		taux= new TauxChange();
		taux1=new TauxChange();
		liste_monnaie= taux_change_service.getAllMonnaie();
		liste_taux= taux_change_service.getAllTaux();
	}
	
	public void updateTaux(){
		
	taux_change_service.updateTaux(taux1);
	liste_taux= taux_change_service.getAllTaux();
	}
	
	public List<TauxChange> getListe_taux() {
		return liste_taux;
	}
	public TauxChange getTaux() {
		return taux;
	}
	public TauxChange getTaux1() {
		return taux1;
	}
	public void setListe_taux(List<TauxChange> liste_taux) {
		this.liste_taux = liste_taux;
	}
	public void setTaux(TauxChange taux) {
		this.taux = taux;
	}
	public void setTaux1(TauxChange taux1) {
		this.taux1 = taux1;
	}
	
	public void addTaux(){
		taux_change_service.addTaux(taux);
		liste_taux= taux_change_service.getAllTaux();
		
	}
	public void deleteTaux(){
		
		taux_change_service.deleteTaux(taux1);
		liste_taux= taux_change_service.getAllTaux();
	}
}
