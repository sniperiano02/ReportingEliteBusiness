package com.reporting.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.reporting.metier.entities.Destination;
import com.reporting.metier.entities.DetailsDestination;
import com.reporting.metier.entities.TypeDestination;
import com.reporting.metier.interfaces.DestinationRemote;
import com.reporting.metier.interfaces.TypeDestinationRemote;


@ManagedBean(name="dest_manage")
@ViewScoped
public class DestinationMbean {
	
	@EJB
	private DestinationRemote dest_service;
	
	@EJB
	private TypeDestinationRemote type_dest_service;
	
	
	private DetailsDestination detail1 ;
	private List<Destination> liste_destination = new ArrayList<>();
	private DetailsDestination detail ;
	private List<DetailsDestination> listeDetails = new ArrayList<>();
	private List<DetailsDestination> listeDetails1 = new ArrayList<>();
	
	private List<TypeDestination> liste_typeDest = new ArrayList<>();
	private Destination destination = new Destination();
	private Destination destination1 = new Destination();
	
	
	private Destination destination2 ;
	
	public Destination getDestination2() {
		return destination2;
	}
	public void setDestination2(Destination destination2) {
		this.destination2 = destination2;
	}
	
	
	public DetailsDestination getDetail() {
		return detail;
	}
	public void setDetail(DetailsDestination detail) {
		this.detail = detail;
	}
	public List<DetailsDestination> getListeDetails() {
		return listeDetails;
	}
	public List<DetailsDestination> getListeDetails1() {
		return listeDetails1;
	}
	public void setListeDetails(List<DetailsDestination> listeDetails) {
		this.listeDetails = listeDetails;
	}
	public void setListeDetails1(List<DetailsDestination> listeDetails1) {
		this.listeDetails1 = listeDetails1;
	}
	public Destination getDestination() {
		return destination;
	}
	public Destination getDestination1() {
		return destination1;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	public void setDestination1(Destination destination1) {
		this.destination1 = destination1;
	}
	public List<Destination> getListe_destination() {
		return liste_destination;
	}
	public void setListe_destination(List<Destination> liste_destination) {
		this.liste_destination = liste_destination;
	}
	public List<TypeDestination> getListe_typeDest() {
		return liste_typeDest;
	}
	public void setListe_typeDest(List<TypeDestination> liste_typeDest) {
		this.liste_typeDest = liste_typeDest;
	}
	
	public DetailsDestination getDetail1() {
		return detail1;
	}
	public void setDetail1(DetailsDestination detail1) {
		this.detail1 = detail1;
	}
	
	
	@PostConstruct
	public void init(){
		
		liste_destination= dest_service.getAllDestination();
liste_typeDest= type_dest_service.getAllTypeDest();
			destination= new Destination();
			listeDetails= new ArrayList<>();
			destination1= new Destination();
		detail = new DetailsDestination();
		destination2= new Destination();
detail1 = new DetailsDestination();
		
	}
	
	
	
	public void updateDestination(){
		dest_service.updateDestination(destination1);
		liste_destination= dest_service.getAllDestination();
	}
	public void deleteDestination(){
		dest_service.deleteDestination(destination1);
		liste_destination= dest_service.getAllDestination();
		
	}
	
	public Destination loadDestination(){
		destination2 = destination1;
		return destination2;
		
	}
	
	
	
	public void addDestination(){
		
		dest_service.addDestination(destination);
		liste_destination= dest_service.getAllDestination();
	
	}
	
	public void addDetail(){

		detail.setDest(destination1);
	destination1.getLst_details().add(detail);
	destination1.setDestination(destination1.getDestination());
	dest_service.addDetail(detail, destination1);
	detail = new DetailsDestination();
		
		
		
	}
	public void deleteDetail(){
	
		destination1.getLst_details().remove(detail1);
		dest_service.deleteDetail(detail1, destination1);
		listeDetails=new ArrayList<>();
		
	}
	

}
