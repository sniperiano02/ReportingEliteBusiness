package com.reporting.mbeans;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DualListModel;

import com.reporting.metier.entities.Destination;
import com.reporting.metier.entities.PackageZone;
import com.reporting.metier.entities.Zone;
import com.reporting.metier.entities.ZonesDestination;
import com.reporting.metier.interfaces.PackageZoneRemote;
import com.reporting.metier.interfaces.ZoneDestinationRemote;
import com.reporting.metier.interfaces.ZoneRemote;


@ManagedBean(name="package_zone")
@ViewScoped
public class PackageZoneMbean {

	
	@EJB 
	private PackageZoneRemote package_zones;
	
	@EJB 
	private ZoneDestinationRemote service;
	
	
	
	private PackageZone selectedPack;
	
	private List<Zone> list_zone_package = new ArrayList<>();
	
	
	public List<Zone> getList_zone_package() {
		return list_zone_package;
	}
	public void setList_zone_package(List<Zone> list_zone_package) {
		this.list_zone_package = list_zone_package;
	}
	
	public PackageZone getSelectedPack() {
		return selectedPack;
	}
	public void setSelectedPack(PackageZone selectedPack) {
		this.selectedPack = selectedPack;
	}
	private List<PackageZone> liste_package_zone;
	
	@EJB
	private ZoneRemote zone_service;
	
	private List<Zone> zones = new ArrayList<>();
	
	private List<Zone> zones_package = new ArrayList<>();
	
	
	private List<ZonesDestination> zonesDest = new ArrayList<>();
	
	private List<ZonesDestination> zoneDest1= new ArrayList<>();

	private Zone zone = new Zone();

	private PackageZone pack;
	private PackageZone pack1;
	
	private ZonesDestination zonedest = new ZonesDestination();
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
	public void init() throws ParseException{
		liste_package_zone= package_zones.getAllPackageZone();
		pack = new PackageZone();
		pack1 = new PackageZone();
		
		zones = zone_service.getAllZones();
		String Dt = "01/06/2014";
		
			selectedPack = new PackageZone();
			selected_zone = new Zone();
		
	 allTabs = new DualListModel<Destination>(tabsSource, tabsTarget);
		

		
	}
	
	
	
	private List<ZonesDestination> liste_zone_destination = new ArrayList<>();
	
	
	public List<ZonesDestination> getListe_zone_destination() {
		return liste_zone_destination;
	}
	public void setListe_zone_destination(
			List<ZonesDestination> liste_zone_destination) {
		this.liste_zone_destination = liste_zone_destination;
	}
	public void loadZones(){
		
		list_zone_package = zone_service.getZonesByPackage(pack1);
	}
	public void loadDestinations(){
		System.out.println(selected_zone.getId());
		liste_zone_destination=  service.getAllZonesDestinationByZone(selected_zone);
	}
	
	public void deleteDestination(){
		selected_zone.getZonesdestinations().remove(zonedest);
		selected_zone.setZonesdestinations(selected_zone.getZonesdestinations());
		
		
		service.deleteZonesDestination(zonedest);
		loadDestinations();
	}
	
	private Date date1 ;
	
	public Date getDate1() {
		return date1;
	}
	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	
	public void change(SelectEvent event){
		DateFormat df = new SimpleDateFormat("dd/MM/YYYY");
		String date = df.format(event.getObject());
		System.out.println(zone.getId());
		destinations = service.getDestinationByDate(date, zone.getId());
		for(int i=0;i<destinations.size();i++){
			
			tabsSource.add(destinations.get(i));
			
		
		
	}
		allTabs = new DualListModel<Destination>(tabsSource, tabsTarget);
		
	}
	
	public void createpack(){
		
		
			package_zones.addPackageZone(pack);
			liste_package_zone= package_zones.getAllPackageZone();
		
		
		
	}
	
	public void supprimer_pack(){
		
		package_zones.DeletePackageZone(pack1);
		liste_package_zone= package_zones.getAllPackageZone();
	}
	
	public void modifier_pack(){
		pack1.setDateModif(new Timestamp(new Date().getTime()));
		package_zones.updatePackageZone(pack1);
		pack1= new PackageZone();
		liste_package_zone= package_zones.getAllPackageZone();
	}
	
	
	public void addZOne(){
	
		zone.setPackagezone(pack1);
		zone_service.addZone(zone);
		
		loadZones();
		
	
	}
public void addZonesDestination(){
	
		System.out.println(this.getZone());
	System.out.println(allTabs.getTarget().size());
	List<ZonesDestination> liste = new ArrayList<>();
	for(Destination d :allTabs.getTarget()){
		
		zonedest = new ZonesDestination();
		zonedest.setZone(this.getSelected_zone());
		zonedest.setDateDebutValidite(date1);
		zonedest.setDestination(d);
		liste.add(zonedest);
	
	}
	
	this.getSelected_zone().setZonesdestinations(liste);
	zone_service.updateZone(this.getSelected_zone());
	zonesDest= zone.getZonesdestinations();
		loadDestinations();
		
		
		
	}


public void deleteZone(){
	pack1.getPackzones().remove(selected_zone);
	pack1.setPackzones(pack1.getPackzones());
	
	
	zone_service.deleteZone(selected_zone);
	loadZones();

}

public void modifZone(){
	zone_service.updateZone(selected_zone);
	
}
public void updatePackage(){
	package_zones.updatePackageZone(pack1);
}
	

public List<Destination> getDestinations() {
	return destinations;
}

	public Zone getZone() {
		return zone;
	}
	
	
	public List<Zone> getZones() {
		return zones;
	}
	public void setZones(List<Zone> zones) {
		this.zones = zones;
	}
	
	private Zone selected_zone= new Zone();
	
	public Zone getSelected_zone() {
		return selected_zone;
	}
	public void setSelected_zone(Zone selected_zone) {
		this.selected_zone = selected_zone;
	}
	
	public List<PackageZone> getListe_package_zone() {
		return liste_package_zone;
	}
	public void setListe_package_zone(List<PackageZone> liste_package_zone) {
		this.liste_package_zone = liste_package_zone;
	}
	public PackageZone getPack() {
		return pack;
	}
	public PackageZone getPack1() {
		return pack1;
	}
	public void setPack(PackageZone pack) {
		this.pack = pack;
	}
	public void setPack1(PackageZone pack1) {
		this.pack1 = pack1;
	}
	
	public void setZone(Zone zone) {
		this.zone = zone;
	}
	public void setZonesDest(List<ZonesDestination> zonesDest) {
		this.zonesDest = zonesDest;
	}
	public List<ZonesDestination> getZoneDest1() {
		return zoneDest1;
	}
	public void setZoneDest1(List<ZonesDestination> zoneDest1) {
		this.zoneDest1 = zoneDest1;
	}

	
	public List<Zone> getZones_package() {
		return zones_package;
	}
	public void setZones_package(List<Zone> zones_package) {
		this.zones_package = zones_package;
	}
	public List<ZonesDestination> getZonesDest() {
		return zonesDest;
	}
	public ZonesDestination getZonedest() {
		return zonedest;
	}
	public void setZonedest(ZonesDestination zonedest) {
		this.zonedest = zonedest;
	}
}
