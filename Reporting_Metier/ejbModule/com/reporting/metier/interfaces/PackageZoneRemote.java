package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.PackageZone;


@Remote
public interface PackageZoneRemote {

	
	public List<PackageZone> getAllPackageZone();
	public void addPackageZone(PackageZone pz);
	public void DeletePackageZone(PackageZone pz);
	public void updatePackageZone(PackageZone pz);
}
