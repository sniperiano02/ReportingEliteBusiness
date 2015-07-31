package com.reporting.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.reporting.metier.entities.TypeDestination;

import com.reporting.metier.interfaces.TypeDestinationRemote;


@ManagedBean(name="typeDest")
@ViewScoped
public class TypeDestMbean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@EJB
	private TypeDestinationRemote typeDestService;
private List<TypeDestination> liste_typeDest= new ArrayList<>();
	
	private TypeDestination typeDest;
	private TypeDestination typeDest1;
	
	
	public List<TypeDestination> getListe_typeDests() {
		return liste_typeDest;
	}
	public void setListe_typeDests(List<TypeDestination> liste_typeDests) {
		this.liste_typeDest = liste_typeDests;
	}
	public void settypeDest(TypeDestination typeDest) {
		this.typeDest = typeDest;
	}
	public void settypeDest1(TypeDestination typeDest1) {
		this.typeDest1 = typeDest1;
	}
	public TypeDestination gettypeDest() {
		return typeDest;
	}
	public TypeDestination gettypeDest1() {
		return typeDest1;
	}
	@PostConstruct
	public void init(){
		liste_typeDest= typeDestService.getAllTypeDest();
		typeDest = new TypeDestination();
		typeDest1= new TypeDestination();
	}
	
	public void addtypeDest(){
		typeDestService.addTypeDest(typeDest);
		liste_typeDest= typeDestService.getAllTypeDest();
		typeDest = new TypeDestination();
	}
public void DeletetypeDest(){
		typeDestService.deleteTypeDest(typeDest1);
		liste_typeDest= typeDestService.getAllTypeDest();
	}
public void updatetypeDest(){
	typeDestService.updateTypeDest(typeDest1);
	liste_typeDest= typeDestService.getAllTypeDest();
}
}