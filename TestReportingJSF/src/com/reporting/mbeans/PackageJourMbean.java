package com.reporting.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.reporting.metier.entities.JoursSemaine;
import com.reporting.metier.entities.PackageJour;
import com.reporting.metier.interfaces.PackageJourRemote;




@ManagedBean(name="package_jour")
@ViewScoped
public class PackageJourMbean {
	
	
	@EJB
	PackageJourRemote package_jour_service;
	
	private PackageJour packagejour;
	private PackageJour packagejour1;
	private List<PackageJour> jours;
	
	private String[] selectedConsoles;
	private String[] selectedConsoles1;
	
	
	
	
	@PostConstruct
	public void init(){
		
		jours=package_jour_service.getAllPackagesJour();
		packagejour= new PackageJour();
		
		
	}
	
	
	public void addPackage(){
		List<JoursSemaine> journees = new ArrayList<>();
		for(int i =0;i<selectedConsoles.length;i++){
			JoursSemaine j = new JoursSemaine();
			j.setJournee(selectedConsoles[i]);
			j.setPackagejour(packagejour);
			journees.add(j);
		}
		packagejour.setJours(journees);
		package_jour_service.addPackage(packagejour);
		jours=package_jour_service.getAllPackagesJour();
	}
	public void modifPackage(){
		
		
		List<JoursSemaine> journees = new ArrayList<JoursSemaine>();
		packagejour1.setJours(journees);
		package_jour_service.updatePackage(packagejour1);
		for(int i =0;i<selectedConsoles1.length;i++){
			System.out.println(selectedConsoles1[i]);
			JoursSemaine j = new JoursSemaine();
			j.setJournee(selectedConsoles1[i]);
			j.setPackagejour(packagejour1);
			journees.add(j);
		}
		packagejour1.setJours(journees);
		package_jour_service.updatePackage(packagejour1);
		jours=package_jour_service.getAllPackagesJour();
	}
	public void deletePackage(){
		package_jour_service.DeletePackage(packagejour1);
		jours=package_jour_service.getAllPackagesJour();
	}
	public String[] getSelectedConsoles1() {
		
		return selectedConsoles1;
	}
	public void setSelectedConsoles1(String[] selectedConsoles1) {
		this.selectedConsoles1 = selectedConsoles1;
	}
	public String[] getSelectedConsoles() {
		return selectedConsoles;
	}
	public void setSelectedConsoles(String[] selectedConsoles) {
		this.selectedConsoles = selectedConsoles;
	}
	public PackageJour getPackagejour() {
		return packagejour;
	}
	public void setPackagejour(PackageJour packagejour) {
		this.packagejour = packagejour;
	}
	public PackageJour getPackagejour1() {
		return packagejour1;
	}
	public void setPackagejour1(PackageJour packagejour1) {
		this.packagejour1 = packagejour1;
	}
	public List<PackageJour> getJours() {
		return jours;
	}
	public void setJours(List<PackageJour> jours) {
		this.jours = jours;
	}
	
	
	
	

}
