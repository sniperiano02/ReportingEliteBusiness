package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.PackageJour;

@Remote
public interface PackageJourRemote {
	
	public List<PackageJour> getAllPackagesJour();
	
	public void addPackage(PackageJour pj);
	public void DeletePackage(PackageJour pj);
	public void updatePackage(PackageJour pj);
}
