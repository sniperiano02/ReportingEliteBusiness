package com.reporting.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.reporting.metier.entities.IntervalHeure;
import com.reporting.metier.interfaces.IntervalleRemote;


@ManagedBean(name="package_heure")
@ViewScoped
public class PackageHeureMbean {

	
	@EJB
	private IntervalleRemote intervalle_service;
	
	private List<IntervalHeure> liste_package = new ArrayList<>();
	
	private IntervalHeure package_heure;
	private IntervalHeure package_heure1;
	@PostConstruct
	public void init(){
		liste_package= intervalle_service.getAllIntervalHeures();
		package_heure = new IntervalHeure();
	}
	
	
	public void ajouterPackage(){
		
		intervalle_service.addIntervalle(package_heure);
		liste_package= intervalle_service.getAllIntervalHeures();
		
	}
	public void modifPackage(){
		
		intervalle_service.UpdateIntervalle(package_heure1);
		liste_package= intervalle_service.getAllIntervalHeures();
		
	}
public void supprimerPackage(){
		
		intervalle_service.DeleteIntervalle(package_heure1);
		liste_package= intervalle_service.getAllIntervalHeures();
		
	}


public List<IntervalHeure> getListe_package() {
	return liste_package;
}


public void setListe_package(List<IntervalHeure> liste_package) {
	this.liste_package = liste_package;
}


public IntervalHeure getPackage_heure() {
	return package_heure;
}


public void setPackage_heure(IntervalHeure package_heure) {
	this.package_heure = package_heure;
}


public IntervalHeure getPackage_heure1() {
	return package_heure1;
}


public void setPackage_heure1(IntervalHeure package_heure1) {
	this.package_heure1 = package_heure1;
}



}
