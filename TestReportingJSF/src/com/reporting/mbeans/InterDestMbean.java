package com.reporting.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.reporting.metier.entities.DetCodeDest;
import com.reporting.metier.entities.DetailsPay;
import com.reporting.metier.entities.InterDest;
import com.reporting.metier.entities.Pay;
import com.reporting.metier.interfaces.DestInterRemote;
import com.reporting.metier.interfaces.PaysRemote;


@ManagedBean(name="inter_dest")
@ViewScoped
public class InterDestMbean {

	
	
	@EJB
	private DestInterRemote interdest_service;
	
	@EJB
	private PaysRemote pays_service;
	
	
	
	private List<InterDest> lst_interdest;
	private List<Pay> lst_pays;
	private List<DetCodeDest> liste_details1;
	
	public List<DetCodeDest> getListe_details1() {
		return liste_details1;
	}
	public void setListe_details1(List<DetCodeDest> liste_details1) {
		this.liste_details1 = liste_details1;
	}
	private InterDest interdest ;
	private InterDest interdest1;
	private List<DetCodeDest> liste_details;
	private DetCodeDest details;
	private DetCodeDest details1;
	private DetCodeDest detailsdest;
	
	
	

	public List<DetCodeDest> getListe_details() {
		return liste_details;
	}
	public void setListe_details(List<DetCodeDest> liste_details) {
		this.liste_details = liste_details;
	}
	
	@PostConstruct
	public void init(){
		lst_interdest=interdest_service.getAllInterDests();
		lst_pays=pays_service.getAllPays();
		interdest= new InterDest();
		interdest1= new InterDest();
		liste_details = new ArrayList<>();
		details= new DetCodeDest();
		details1 = new DetCodeDest();
		liste_details1= new ArrayList<>();
		detailsdest= new DetCodeDest();
	}
	
	
	public void ajouterInterDest(){
		for(int i=0;i<liste_details.size();i++){
			liste_details.get(i).setInterdest(interdest);
		}
		interdest.setList_details(liste_details);
		interdest_service.addInterDest(interdest);
		liste_details = new ArrayList<>();
		lst_interdest=interdest_service.getAllInterDests();
		interdest= new InterDest();
	}
	public void SupprimerInterDest(){
		interdest_service.DeleteInterDest(interdest1);
		lst_interdest=interdest_service.getAllInterDests();
	}
	public void ModifierInterDest(){
		
		
interdest1.setList_details(interdest1.getList_details());
		interdest_service.UpdateInterDest(interdest1);
		lst_interdest=interdest_service.getAllInterDests();
	}
	
	public void deleteDetails(){
		liste_details.remove(detailsdest);
		details1= new DetCodeDest();
	}

	public void deleteDetails1(){
	interdest1.getList_details().remove(details1);
	liste_details1= interdest1.getList_details();
	System.out.println(interdest1.getList_details().size());
	interdest1.setList_details(interdest1.getList_details());
	lst_interdest=interdest_service.getAllInterDests();
		details1= new DetCodeDest();
		
	}
	
	
	public void AjouterDetails(){
		liste_details.add(detailsdest);
	System.out.println(detailsdest.getCodeDest());
		detailsdest= new DetCodeDest();
	}
	public void AjouterDetails1(){
		details.setInterdest(interdest);
		
		interdest1.getList_details().add(details);
		
		details= new DetCodeDest();
//	System.out.println(interdest1.getList_details().get(1).getCodePays());
	}
	public List<InterDest> getLst_interdest() {
		return lst_interdest;
	}
	public void setLst_interdest(List<InterDest> lst_interdest) {
		this.lst_interdest = lst_interdest;
	}
	public InterDest getInterdest() {
		return interdest;
	}
	public void setInterdest(InterDest interdest) {
		this.interdest = interdest;
	}
	public InterDest getInterdest1() {
		return interdest1;
	}
	public void setInterdest1(InterDest interdest1) {
		this.interdest1 = interdest1;
	}
	public DetCodeDest getDetailsdest() {
		return detailsdest;
	}
	public void setDetailsdest(DetCodeDest detailsdest) {
		this.detailsdest = detailsdest;
	}
	
	public DetCodeDest getDetails1() {
		return details1;
	}
	public void setDetails1(DetCodeDest details1) {
		this.details1 = details1;
	}
	
	public DetCodeDest getDetails() {
		return details;
	}
	public void setDetails(DetCodeDest details) {
		this.details = details;
	}
	public List<Pay> getLst_pays() {
		return lst_pays;
	}
	public void setLst_pays(List<Pay> lst_pays) {
		this.lst_pays = lst_pays;
	}
}
