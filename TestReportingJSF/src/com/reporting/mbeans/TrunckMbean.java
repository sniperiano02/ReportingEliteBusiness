package com.reporting.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;











import com.reporting.metier.entities.Monnaie;
import com.reporting.metier.entities.Noeud;
import com.reporting.metier.entities.OperateurInterco;
import com.reporting.metier.entities.Pay;
import com.reporting.metier.entities.Trunck;
import com.reporting.metier.entities.TypeDestination;

import com.reporting.metier.interfaces.NoeudRemote;
import com.reporting.metier.interfaces.OperateurIntercoRemote;
import com.reporting.metier.interfaces.PaysRemote;
import com.reporting.metier.interfaces.TrunckRemote;



@ManagedBean(name="trunck")
@ViewScoped
public class TrunckMbean {

	
	
	@EJB
	private OperateurIntercoRemote oper_service;
	
	@EJB
	private TrunckRemote trunck_service;
	
	@EJB
	private NoeudRemote noeud_service;
	
	
	private List<Trunck> lst_trunck;
	private List<OperateurInterco> lst_oper = new ArrayList<>();
	private List<Noeud> lst_noeud = new ArrayList<>();
	
	private Trunck trunck ;
	private Trunck trunck1;
	
	@PostConstruct
	public void init(){
		lst_trunck=trunck_service.getAllTruncks();
		lst_oper=oper_service.getAllOperateurs();
		lst_noeud=noeud_service.getNoeudNoms();
		trunck= new Trunck();
		trunck1= new Trunck();
//		liste_details = new ArrayList<>();
//		details= new DetailsPay();
//		details1 = new DetailsPay();
//		liste_details1= new ArrayList<>();
//		detailsPay= new DetailsPay();
	}
	
	
	public void ajouterTrunck(){
//		for(int i=0;i<liste_details.size();i++){
//			liste_details.get(i).setPays(pays);
//		}
	//	pays.setList_details(liste_details);
		trunck_service.addTrunck(trunck);
		//liste_details = new ArrayList<>();
		lst_trunck=trunck_service.getAllTruncks();
		trunck= new Trunck();
	}
	public void SupprimerTrunck(){
		trunck_service.DeleteTrunck(trunck1);
		lst_trunck=trunck_service.getAllTruncks();
	}
	public void ModifierTrunck(){

		trunck_service.UpdateTrunck(trunck1);
		lst_trunck=trunck_service.getAllTruncks();
	}
	
	
	
	public List<OperateurInterco> getLst_oper() {
		return lst_oper;
	}
	public void setLst_oper(List<OperateurInterco> lst_oper) {
		this.lst_oper = lst_oper;
	}
	

	public List<Trunck> getLst_trunck() {
		return lst_trunck;
	}


	public void setLst_trunck(List<Trunck> lst_trunck) {
		this.lst_trunck = lst_trunck;
	}


	public Trunck getTrunck() {
		return trunck;
	}


	public void setTrunck(Trunck trunck) {
		this.trunck = trunck;
	}


	public Trunck getTrunck1() {
		return trunck1;
	}


	public void setTrunck1(Trunck trunck1) {
		this.trunck1 = trunck1;
	}


	public List<Noeud> getLst_noeud() {
		return lst_noeud;
	}


	public void setLst_noeud(List<Noeud> lst_noeud) {
		this.lst_noeud = lst_noeud;
	}
}
