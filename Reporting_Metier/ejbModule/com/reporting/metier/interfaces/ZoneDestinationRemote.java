package com.reporting.metier.interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.Destination;
import com.reporting.metier.entities.Zone;
import com.reporting.metier.entities.ZonesDestination;
@Remote
public interface ZoneDestinationRemote {

	
	public List<ZonesDestination> getAllZonesDestinationByZone(Zone z);
	
	public List<Destination> getDestinationByDate(String dt , Integer id_zone);
	
	
	public void addZonesDestination(ZonesDestination dest);
	public void deleteZonesDestination(ZonesDestination dest);
	
	public void updateZonesDestination(ZonesDestination dest);
}
