package com.reporting.mbeans.roamingin;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.reporting.metier.entities.IntervalHeure;
import com.reporting.metier.entities.PackageJour;
import com.reporting.metier.entities.PackageRoaming;
import com.reporting.metier.entities.PackageZone;
import com.reporting.metier.entities.PlanTarifaire;
import com.reporting.metier.entities.Service;
import com.reporting.metier.entities.Tarif;
import com.reporting.metier.entities.TarifsRoaIn;
import com.reporting.metier.entities.TarifsRoaIn;
import com.reporting.metier.entities.TypePalier;
import com.reporting.metier.entities.Zone;
import com.reporting.metier.interfaces.IntervalleRemote;
import com.reporting.metier.interfaces.PackageJourRemote;
import com.reporting.metier.interfaces.PackageRoamingRemote;
import com.reporting.metier.interfaces.PackageZoneRemote;
import com.reporting.metier.interfaces.PlanTarifaireRemote;
import com.reporting.metier.interfaces.ServiceRemote;
import com.reporting.metier.interfaces.TarifRoaINRemote;
import com.reporting.metier.interfaces.TarifRoaOutRemote;
import com.reporting.metier.interfaces.TypePalierRemote;
import com.reporting.metier.interfaces.ZoneRemote;


@ManagedBean(name="tarif_gestion_in")
@ViewScoped
public class TarifRoaINMbean {

	@EJB
	private TarifRoaINRemote tarif_service;
	
	@EJB
	private PackageRoamingRemote plan_service;
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
	
	private List<TarifsRoaIn> filtredTarif;
	
	public List<TarifsRoaIn> getFiltredTarif() {
		return filtredTarif;
	}
	public void setFiltredTarif(List<TarifsRoaIn> filtredTarif) {
		this.filtredTarif = filtredTarif;
	}
	
	private List<TarifsRoaIn> allTarifs = new ArrayList<>();
	private TarifsRoaIn tarif = new TarifsRoaIn();
	private List<PackageRoaming> plans=new ArrayList<>();
	private List<PackageZone> package_zones = new ArrayList<>();
	private List<Zone> zones = new ArrayList<>();
	private List<Service> services = new ArrayList<>();
	private List<PackageJour> jours = new ArrayList<>();
	private List<IntervalHeure> intervalles = new ArrayList<>();

	private TarifsRoaIn tarif1;
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
		tarif1 = new TarifsRoaIn();
		
		allTarifs= tarif_service.getAllTarifsRoaOut();
		plans = plan_service.getAllPackage();
		package_zones = package_zone_service.getAllPackageZone();
	
		services= service_manage.getAllService();
		jours= package_jour_service.getAllPackagesJour();
		intervalles = intervalle_service.getAllIntervalHeures();
		paliers= palier_service.getAllTypePaliers();
		zones= new ArrayList<>();
		
	}
	
	public void addTarif(){
		
		tarif_service.addTarifRoaOut(tarif1);
		allTarifs= tarif_service.getAllTarifsRoaOut();
	}
	public void updateTarifRoaOut(){
		
		tarif_service.updateTarifRoaOut(tarif);
		allTarifs= tarif_service.getAllTarifsRoaOut();
	}
	public void deleteTarifRoaOut(){
		tarif_service.deleteTarifRoaOut(tarif);
		allTarifs= tarif_service.getAllTarifsRoaOut();
	}


	public List<IntervalHeure> getIntervalles() {
		return intervalles;
	}
	public void setIntervalles(List<IntervalHeure> intervalles) {
		this.intervalles = intervalles;
	}

public List<TarifsRoaIn> getAllTarifs() {
	return allTarifs;
}
public void setAllTarifs(List<TarifsRoaIn> allTarifs) {
	this.allTarifs = allTarifs;
}


	public TarifsRoaIn getTarif() {
		return tarif;
	}




public void changePackage(){
	
	zones= zone_service.getZonesByPackage(tarif.getPackZone());
}
public void changePackage1(){
	
	zones= zone_service.getZonesByPackage(tarif1.getPackZone());
}


	public void setTarif(TarifsRoaIn tarif) {
		this.tarif = tarif;
	}


public TarifsRoaIn getTarif1() {
	return tarif1;
}
public void setTarif1(TarifsRoaIn tarif1) {
	this.tarif1 = tarif1;
}



	public List<PackageRoaming> getPlans() {
		return plans;
	}
	public void setPlans(List<PackageRoaming> plans) {
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
