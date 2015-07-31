package com.reporting.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.reporting.metier.entities.DetailsPay;
import com.reporting.metier.entities.Pay;
import com.reporting.metier.interfaces.PaysRemote;


@ManagedBean(name="pays")
@ViewScoped
public class PaysMbean {

	
	
	@EJB
	private PaysRemote pays_service;
	
	
	
	private List<Pay> lst_pays;
	private List<DetailsPay> liste_details1;
	
	public List<DetailsPay> getListe_details1() {
		return liste_details1;
	}
	public void setListe_details1(List<DetailsPay> liste_details1) {
		this.liste_details1 = liste_details1;
	}
	private Pay pays ;
	private Pay pays1;
	private List<DetailsPay> liste_details;
	private DetailsPay details;
	private DetailsPay details1;
	private DetailsPay detailsPay;
	
	
	public DetailsPay getDetailsPay() {
		return detailsPay;
	}
	public void setDetailsPay(DetailsPay detailsPay) {
		this.detailsPay = detailsPay;
	}
	
	public DetailsPay getDetails() {
		return details;
	}
	public void setDetails(DetailsPay details) {
		this.details = details;
	}
	public DetailsPay getDetails1() {
		return details1;
	}
	public void setDetails1(DetailsPay details1) {
		this.details1 = details1;
	}
	
	
	public List<Pay> getLst_pays() {
		return lst_pays;
	}
	public void setPays(Pay pays) {
		this.pays = pays;
	}

	
	public Pay getPays1() {
		return pays1;
	}
	public void setPays1(Pay pays1) {
		this.pays1 = pays1;
	}
	public List<DetailsPay> getListe_details() {
		return liste_details;
	}
	public void setListe_details(List<DetailsPay> liste_details) {
		this.liste_details = liste_details;
	}
	public Pay getPays() {
		return pays;
	}
	public void setLst_pays(List<Pay> lst_pays) {
		this.lst_pays = lst_pays;
	}
	@PostConstruct
	public void init(){
		lst_pays=pays_service.getAllPays();
		pays= new Pay();
		pays1= new Pay();
		liste_details = new ArrayList<>();
		details= new DetailsPay();
		details1 = new DetailsPay();
		liste_details1= new ArrayList<>();
		detailsPay= new DetailsPay();
	}
	
	
	public void ajouterPays(){
		for(int i=0;i<liste_details.size();i++){
			liste_details.get(i).setPays(pays);
		}
		pays.setList_details(liste_details);
		pays_service.addPays(pays);
		liste_details = new ArrayList<>();
		lst_pays=pays_service.getAllPays();
		pays= new Pay();
	}
	public void SupprimerPays(){
		pays_service.DeletePays(pays1);
		lst_pays=pays_service.getAllPays();
	}
	public void ModifierPays(){
		
		
pays1.setList_details(pays1.getList_details());
		pays_service.UpdatePays(pays1);
		lst_pays=pays_service.getAllPays();
	}
	
	public void deleteDetails(){
		liste_details.remove(detailsPay);
		details1= new DetailsPay();
	}

	public void deleteDetails1(){
	pays1.getList_details().remove(details1);
	liste_details1= pays1.getList_details();
	System.out.println(pays1.getList_details().size());
	pays1.setList_details(pays1.getList_details());
	lst_pays=pays_service.getAllPays();
		details1= new DetailsPay();
		
	}
	
	
	public void AjouterDetails(){
		liste_details.add(detailsPay);
	System.out.println(detailsPay.getCodePays());
		detailsPay= new DetailsPay();
	}
	public void AjouterDetails1(){
		details.setPays(pays1);
		
		pays1.getList_details().add(details);
		
		details= new DetailsPay();
	System.out.println(pays1.getList_details().get(1).getCodePays());
	}
}
