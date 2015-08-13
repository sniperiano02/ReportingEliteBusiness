package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.PackageZone;
import com.reporting.metier.entities.Zone;


@Remote
public interface ZoneRemote {

	
	public List<Zone> getAllZones();
	
	public void addZone(Zone z);
	
	public void updateZone(Zone z);
	
	
	public List<Zone> getZonesByPackage(PackageZone pack);
	public List<Zone> getZonesByIdPackage(Integer id_package);
	public void deleteZone(Zone z);
}
