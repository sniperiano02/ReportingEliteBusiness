package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.PackageRoaming;
import com.reporting.metier.entities.RoaminginParameter;


@Remote
public interface PackageRoamingRemote {

	
	public List<PackageRoaming> getAllPackage();
	
	public void addPackageRomaing(PackageRoaming pr);
	public void deletePackageRomaing(PackageRoaming pr);
	public void updatePackageRomaing(PackageRoaming pr);
	public void priseEncomptePackageRomaing(RoaminginParameter rp);
	
}
