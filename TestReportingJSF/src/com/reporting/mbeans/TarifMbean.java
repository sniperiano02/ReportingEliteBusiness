package com.reporting.mbeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.reporting.metier.entities.IntervalHeure;
import com.reporting.metier.entities.PackageJour;
import com.reporting.metier.entities.PackageZone;
import com.reporting.metier.entities.PlanTarifaire;
import com.reporting.metier.entities.Service;
import com.reporting.metier.entities.Tarif;
import com.reporting.metier.entities.TypePalier;
import com.reporting.metier.entities.Zone;
import com.reporting.metier.interfaces.IntervalleRemote;
import com.reporting.metier.interfaces.PackageJourRemote;
import com.reporting.metier.interfaces.PackageZoneRemote;
import com.reporting.metier.interfaces.PlanTarifaireRemote;
import com.reporting.metier.interfaces.ServiceRemote;
import com.reporting.metier.interfaces.TarifRemote;
import com.reporting.metier.interfaces.TypePalierRemote;
import com.reporting.metier.interfaces.ZoneRemote;


@ManagedBean(name="tarif_gestion")
@ViewScoped
public class TarifMbean {

	@EJB
	private TarifRemote tarif_service;
	
	
	private LinkedHashSet<String> dates;
	
	
	
public LinkedHashSet<String> getDates() {
	return dates;
}
public void setDates(LinkedHashSet<String> dates) {
	this.dates = dates;
}
	
	@EJB
	private PlanTarifaireRemote plan_service;
	@EJB
	private PackageZoneRemote package_zone_service;
	
	@EJB
	private PackageJourRemote package_jour_service;
	@EJB
	private ServiceRemote service_manage;
	
	@EJB
	private ZoneRemote zone_service;
	
	@EJB
	private IntervalleRemote intervalle_service;
	
	private List<Tarif> filtredTarif;
	
	public List<Tarif> getFiltredTarif() {
		return filtredTarif;
	}
	public void setFiltredTarif(List<Tarif> filtredTarif) {
		this.filtredTarif = filtredTarif;
	}
	
	private List<Tarif> allTarifs = new ArrayList<>();
	private Tarif tarif = new Tarif();
	private List<PlanTarifaire> plans=new ArrayList<>();
	private List<PackageZone> package_zones = new ArrayList<>();
	private List<Zone> zones = new ArrayList<>();
	private List<Service> services = new ArrayList<>();
	private List<PackageJour> jours = new ArrayList<>();
	private List<IntervalHeure> intervalles = new ArrayList<>();
	
	private Tarif tarif1;
	private List<TypePalier> paliers= new ArrayList<>();
	
	public List<TypePalier> getPaliers() {
		return paliers;
	}
	public void setPaliers(List<TypePalier> paliers) {
		this.paliers = paliers;
	}
	
	@EJB
	TypePalierRemote palier_service;
	
	@PostConstruct
	public void init(){
		tarif1 = new Tarif();
		
		allTarifs= tarif_service.getAllTarifs();
		List<String> dates1 = new ArrayList<>();
		for(int i =0;i<allTarifs.size();i++){
				dates1.add(allTarifs.get(i).getDateDebutValidite().toString());
				
			
			
			
		}
		dates = new LinkedHashSet<String>(dates1);
		plans = plan_service.getAllPlanTarifaires();
		
		package_zones = package_zone_service.getAllPackageZone();
		zones = zone_service.getAllZones();
		services= service_manage.getAllService();
		jours= package_jour_service.getAllPackagesJour();
		intervalles = intervalle_service.getAllIntervalHeures();
		paliers= palier_service.getAllTypePaliers();
		
	}
	
	public void addTarif(){
		
		tarif_service.addTarif(tarif1);
		allTarifs= tarif_service.getAllTarifs();
	}
	public void updateTarif(){
		
		tarif_service.updateTarif(tarif);
		allTarifs= tarif_service.getAllTarifs();
	}
	public void deleteTarif(){
		tarif_service.deleteTarif(tarif);
		allTarifs= tarif_service.getAllTarifs();
	}


	public List<IntervalHeure> getIntervalles() {
		return intervalles;
	}
	public void setIntervalles(List<IntervalHeure> intervalles) {
		this.intervalles = intervalles;
	}

public List<Tarif> getAllTarifs() {
	return allTarifs;
}
public void setAllTarifs(List<Tarif> allTarifs) {
	this.allTarifs = allTarifs;
}


	public Tarif getTarif() {
		return tarif;
	}






	public void setTarif(Tarif tarif) {
		this.tarif = tarif;
	}


public Tarif getTarif1() {
	return tarif1;
}
public void setTarif1(Tarif tarif1) {
	this.tarif1 = tarif1;
}



	public List<PlanTarifaire> getPlans() {
		return plans;
	}






	public void setPlans(List<PlanTarifaire> plans) {
		this.plans = plans;
	}






	public List<PackageZone> getPackage_zones() {
		return package_zones;
	}






	public void setPackage_zones(List<PackageZone> package_zones) {
		this.package_zones = package_zones;
	}






	public List<Zone> getZones() {
		return zones;
	}






	public void setZones(List<Zone> zones) {
		this.zones = zones;
	}






	public List<Service> getServices() {
		return services;
	}






	public void setServices(List<Service> services) {
		this.services = services;
	}






	public List<PackageJour> getJours() {
		return jours;
	}






	public void setJours(List<PackageJour> jours) {
		this.jours = jours;
	}
	
}
