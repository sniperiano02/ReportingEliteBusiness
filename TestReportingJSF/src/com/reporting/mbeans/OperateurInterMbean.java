package com.reporting.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;







import com.reporting.metier.entities.Monnaie;
import com.reporting.metier.entities.OperateurInterco;
import com.reporting.metier.entities.Pay;
import com.reporting.metier.entities.TypeDestination;
import com.reporting.metier.interfaces.MonnaiRemote;
import com.reporting.metier.interfaces.OperateurIntercoRemote;
import com.reporting.metier.interfaces.PaysRemote;



@ManagedBean(name="operateur")
@ViewScoped
public class OperateurInterMbean {

	
	
	@EJB
	private OperateurIntercoRemote oper_service;
	
	@EJB
	private PaysRemote pays_service;
	
	@EJB
	private MonnaiRemote monnai_service;
	
	private List<OperateurInterco> lst_oper;
	private List<Pay> liste_pays = new ArrayList<>();
	private List<Monnaie> liste_monnai = new ArrayList<>();
	
	private OperateurInterco operateur ;
	private OperateurInterco operateur1;
//	private List<DetailsPay> liste_details;
//	private DetailsPay details;
//	private DetailsPay details1;
//	private DetailsPay detailsPay;
	
	
//	public DetailsPay getDetailsPay() {
//		return detailsPay;
//	}
//	public void setDetailsPay(DetailsPay detailsPay) {
//		this.detailsPay = detailsPay;
//	}
//	
//	public DetailsPay getDetails() {
//		return details;
//	}
//	public void setDetails(DetailsPay details) {
//		this.details = details;
//	}
//	public DetailsPay getDetails1() {
//		return details1;
//	}
//	public void setDetails1(DetailsPay details1) {
//		this.details1 = details1;
//	}
	
	
//	public List<DetailsPay> getListe_details() {
//		return liste_details;
//	}
//	public void setListe_details(List<DetailsPay> liste_details) {
//		this.liste_details = liste_details;
//	}
//	
	@PostConstruct
	public void init(){
		liste_monnai= monnai_service.getAllMonnaies();
		liste_pays= pays_service.getAllPays();
		lst_oper=oper_service.getAllOperateurs();
		operateur= new OperateurInterco();
		operateur1= new OperateurInterco();
//		liste_details = new ArrayList<>();
//		details= new DetailsPay();
//		details1 = new DetailsPay();
//		liste_details1= new ArrayList<>();
//		detailsPay= new DetailsPay();
	}
	
	
	public void ajouterOperateur(){
//		for(int i=0;i<liste_details.size();i++){
//			liste_details.get(i).setPays(pays);
//		}
	//	pays.setList_details(liste_details);
		oper_service.addOperateur(operateur);
		//liste_details = new ArrayList<>();
		lst_oper=oper_service.getAllOperateurs();
		operateur= new OperateurInterco();
	}
	public void SupprimerOperateur(){
		oper_service.DeleteOperateur(operateur1);
		lst_oper=oper_service.getAllOperateurs();
	}
	public void ModifierOperateur(){

		oper_service.UpdateOperateur(operateur1);
		lst_oper=oper_service.getAllOperateurs();
	}
	
	
	public OperateurInterco getOperateur() {
		return operateur;
	}
	public void setOperateur(OperateurInterco operateur) {
		this.operateur = operateur;
	}
	public List<OperateurInterco> getLst_oper() {
		return lst_oper;
	}
	public void setLst_oper(List<OperateurInterco> lst_oper) {
		this.lst_oper = lst_oper;
	}
	public OperateurInterco getOperateur1() {
		return operateur1;
	}
	public void setOperateur1(OperateurInterco operateur1) {
		this.operateur1 = operateur1;
	}


	public List<Pay> getListe_pays() {
		return liste_pays;
	}


	public void setListe_pays(List<Pay> liste_pays) {
		this.liste_pays = liste_pays;
	}


	public List<Monnaie> getListe_monnai() {
		return liste_monnai;
	}


	public void setListe_monnai(List<Monnaie> liste_monnai) {
		this.liste_monnai = liste_monnai;
	}
}
