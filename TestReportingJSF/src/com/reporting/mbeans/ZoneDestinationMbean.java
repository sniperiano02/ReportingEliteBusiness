package com.reporting.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DualListModel;

import com.reporting.metier.entities.Destination;
import com.reporting.metier.entities.GuiGroup;
import com.reporting.metier.entities.Destination;
import com.reporting.metier.entities.Destination;
import com.reporting.metier.entities.GuiTabsGroup;
import com.reporting.metier.entities.Zone;
import com.reporting.metier.entities.ZonesDestination;
import com.reporting.metier.interfaces.DestinationRemote;
import com.reporting.metier.interfaces.ZoneDestinationRemote;
import com.reporting.metier.interfaces.ZoneRemote;

@ManagedBean(name="zoneDest")
@ViewScoped
public class ZoneDestinationMbean implements Serializable {

	
	
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3603450826101777233L;


	@EJB
 ZoneDestinationRemote service;
	
@EJB
private ZoneRemote zoneService;

@EJB
private DestinationRemote destination_service;

	private ZonesDestination ZonesDestination;
	private ZonesDestination ZonesDestination1;
	private List<ZonesDestination> liste_ZonesDestinations= new ArrayList<>();

	private List<Zone> zones = new ArrayList<>();
	private List<Destination> destinations = new ArrayList<>();
	
	
	
	  private List<Destination> tabsSource = new ArrayList<Destination>();
   private  List<Destination> tabsTarget = new ArrayList<Destination>();
	
    private DualListModel<Destination> allTabs ;
	
	

    public DualListModel<Destination> getAllTabs() {
		return allTabs;
	}
	public void setAllTabs(DualListModel<Destination> allTabs) {
		this.allTabs = allTabs;
	}
	public List<Destination> getTabsSource() {
		return tabsSource;
	}
	public void setTabsSource(List<Destination> tabsSource) {
		this.tabsSource = tabsSource;
	}
	public List<Destination> getTabsTarget() {
		return tabsTarget;
	}
	public void setTabsTarget(List<Destination> tabsTarget) {
		this.tabsTarget = tabsTarget;
	}
	


	@PostConstruct
	public void init(){
		//liste_ZonesDestinations= service.getAllZonesDestination();
		ZonesDestination = new ZonesDestination();
		ZonesDestination1= new ZonesDestination();
		zones= zoneService.getAllZones();
		destinations= destination_service.getAllDestination();
		for(int i=0;i<destinations.size();i++){
			
			
				tabsSource.add(destinations.get(i));
				
			
			
		}
		 allTabs = new DualListModel<Destination>(tabsSource, tabsTarget);
	}
	public List<Destination> getDestinations() {
		return destinations;
	}
	public void setDestinations(List<Destination> destinations) {
		this.destinations = destinations;
	}
	public void addZonesDestination(){
		
		service.addZonesDestination(ZonesDestination);
		//liste_ZonesDestinations= service.getAllZonesDestination();
		ZonesDestination = new ZonesDestination();
	}
public void DeleteZonesDestination(){
	
		service.deleteZonesDestination(ZonesDestination1);
		//liste_ZonesDestinations= service.getAllZonesDestination();
	}
public void updateZonesDestination(){
	
	service.updateZonesDestination(ZonesDestination1);
	//liste_ZonesDestinations= service.getAllZonesDestination();
	ZonesDestination1= new ZonesDestination();
}
public List<Zone> getZones() {
	return zones;
}
public void setZones(List<Zone> zones) {
	this.zones = zones;
}
public List<ZonesDestination> getListe_ZonesDestinations() {
	return liste_ZonesDestinations;
}
public void setListe_ZonesDestinations(List<ZonesDestination> liste_ZonesDestinations) {
	this.liste_ZonesDestinations = liste_ZonesDestinations;
}
public void setZonesDestination(ZonesDestination ZonesDestination) {
	this.ZonesDestination = ZonesDestination;
}
public void setZonesDestination1(ZonesDestination ZonesDestination1) {
	this.ZonesDestination1 = ZonesDestination1;
}
public ZonesDestination getZonesDestination() {
	return ZonesDestination;
}
public ZonesDestination getZonesDestination1() {
	return ZonesDestination1;
}
}
