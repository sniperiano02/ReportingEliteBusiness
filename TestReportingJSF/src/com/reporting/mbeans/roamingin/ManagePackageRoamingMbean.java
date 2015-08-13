package com.reporting.mbeans.roamingin;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.reporting.metier.entities.PackageRoaming;
import com.reporting.metier.entities.RoaminginParameter;
import com.reporting.metier.interfaces.PackageRoamingRemote;


@ManagedBean(name="manage_package_roaming")
@ViewScoped
public class ManagePackageRoamingMbean {

	
	
	@EJB
	private PackageRoamingRemote package_roaming_service;
	
	private List<PackageRoaming> lst_package_roaming;
	
	private PackageRoaming package_roaming;
	private PackageRoaming package_roaming1;
	
	private RoaminginParameter roaminginParameter;
	
	private String etat;
	
	
	@PostConstruct
	public void init(){
		
		lst_package_roaming= package_roaming_service.getAllPackage();
		package_roaming= new PackageRoaming();
		package_roaming1= new PackageRoaming();
		roaminginParameter = new RoaminginParameter();
	}
	
	public void addPackageRoaming(){
		
		package_roaming_service.addPackageRomaing(package_roaming);
		lst_package_roaming= package_roaming_service.getAllPackage();
	}
	
	
	
	public void updatePackageRoaming(){
		package_roaming_service.updatePackageRomaing(package_roaming1);
		lst_package_roaming= package_roaming_service.getAllPackage();
	}
	public void deletePackageRoaming(){
		package_roaming_service.updatePackageRomaing(package_roaming1);
		lst_package_roaming= package_roaming_service.getAllPackage();
	}

	public List<PackageRoaming> getLst_package_roaming() {
		return lst_package_roaming;
	}

	public void setLst_package_roaming(List<PackageRoaming> lst_package_roaming) {
		this.lst_package_roaming = lst_package_roaming;
	}

	public PackageRoaming getPackage_roaming() {
		return package_roaming;
	}

	public void setPackage_roaming(PackageRoaming package_roaming) {
		this.package_roaming = package_roaming;
	}

	public PackageRoaming getPackage_roaming1() {
		return package_roaming1;
	}

	public void setPackage_roaming1(PackageRoaming package_roaming1) {
		this.package_roaming1 = package_roaming1;
	}

	public RoaminginParameter getRoaminginParameter() {
		return roaminginParameter;
	}

	public void setRoaminginParameter(RoaminginParameter roaminginParameter) {
		this.roaminginParameter = roaminginParameter;
	}
	
	
	
}
