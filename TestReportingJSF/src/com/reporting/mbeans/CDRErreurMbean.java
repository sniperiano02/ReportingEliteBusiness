package com.reporting.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.reporting.metier.entities.StatErreur;
import com.reporting.metier.interfaces.StatErreurRemote;


@ManagedBean(name="cdr_erreurs")
@ViewScoped
public class CDRErreurMbean {

	
	
	@EJB
	private StatErreurRemote stat_erreurs_service;
	
	
	private List<StatErreur> listeUtarif = new ArrayList<>();
	private List<StatErreur> listeUZone  = new ArrayList<>();
	private List<StatErreur> listeUDest  = new ArrayList<>();
	private List<StatErreur> listeUHni  = new ArrayList<>();
	
	
	@PostConstruct
	public void init(){
		listeUtarif= stat_erreurs_service.getStatsErreur("UTARIF");
		listeUZone = stat_erreurs_service.getStatsErreur("UZONE");
		listeUDest= stat_erreurs_service.getStatsErreur("UDEST");
		listeUHni= stat_erreurs_service.getStatsErreur("UHNI");
	}
	
	public List<StatErreur> getListeUDest() {
		return listeUDest;
	}
	public void setListeUDest(List<StatErreur> listeUDest) {
		this.listeUDest = listeUDest;
	}

	public List<StatErreur> getListeUtarif() {
		return listeUtarif;
	}

	public void setListeUtarif(List<StatErreur> listeUtarif) {
		this.listeUtarif = listeUtarif;
	}

	public List<StatErreur> getListeUZone() {
		return listeUZone;
	}

	public void setListeUZone(List<StatErreur> listeUZone) {
		this.listeUZone = listeUZone;
	}

	public List<StatErreur> getListeUHni() {
		return listeUHni;
	}

	public void setListeUHni(List<StatErreur> listeUHni) {
		this.listeUHni = listeUHni;
	}
	
	
}
