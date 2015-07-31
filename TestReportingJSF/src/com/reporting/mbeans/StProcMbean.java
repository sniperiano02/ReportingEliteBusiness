package com.reporting.mbeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.reporting.metier.entities.Proccontrole;
import com.reporting.metier.entities.Stproc;
import com.reporting.metier.interfaces.StProcRemote;


@ManagedBean(name="proc_manage")
@ViewScoped
public class StProcMbean implements Serializable {

	
	@EJB
	private StProcRemote stProcService;
	
	
	private List<Proccontrole> liste_details = new ArrayList<>();
	
	
	public List<Proccontrole> getListe_details() {
		return liste_details;
	}
	public void setListe_details(List<Proccontrole> liste_details) {
		this.liste_details = liste_details;
	}
	
	
	private Stproc proc = new Stproc();
	
	public Stproc getProc() {
		return proc;
	}
	public void setProc(Stproc proc) {
		this.proc = proc;
	}
	
	private List<Stproc> liste_proc = new ArrayList<>();
	
	
	public List<Stproc> getListe_proc() {
		return liste_proc;
	}
	public void setListe_proc(List<Stproc> liste_proc) {
		this.liste_proc = liste_proc;
	}
	@PostConstruct
	public void init(){
		liste_proc=stProcService.getAllStProc();
		
	}
	
	
	public void getDetails(){
		
		liste_details = stProcService.getDetailsByName(proc.getProc());
	}
	
	public void arretProc(){
		proc.setEtatlance(2);
		stProcService.arretProc(proc);
		liste_proc=stProcService.getAllStProc();
	}
	
	public void activerProc(){
		proc.setEtatlance(1);
		stProcService.activerProc(proc);
		liste_proc=stProcService.getAllStProc();
	}
	public void changerCycle(){
		
		stProcService.changerCycle(proc);
		liste_proc=stProcService.getAllStProc();
	}
}
