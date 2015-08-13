package com.reporting.mbeans.roamingout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;


import com.reporting.metier.entities.TarifsRoaPop;
import com.reporting.metier.interfaces.TarifPopRoamRemote;


@ManagedBean(name="tarif_pop")
@ViewScoped
public class TarifPopRoamMbean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3488417247629137575L;
	
	
	@EJB
	private TarifPopRoamRemote plan_remote;
	
	private TarifsRoaPop tarifPop ;
	
	private TarifsRoaPop plan;
	
	public TarifsRoaPop getPlan() {
		return plan;
	}
	public void setPlan(TarifsRoaPop plan) {
		this.plan = plan;
	}
	

	
	private List<TarifsRoaPop> list_plans = new ArrayList<TarifsRoaPop>();
	
	public TarifPopRoamMbean() {
		// TODO Auto-generated constructor stub
	}
	@PostConstruct
	public void init(){
		tarifPop = new TarifsRoaPop();
		list_plans= plan_remote.getAllTarifPop();
	}
	
	
	public void createTarifPop(){


		plan_remote.createTarifPop(tarifPop);
		list_plans= plan_remote.getAllTarifPop();
		tarifPop = new TarifsRoaPop();
	}
	public void updateTarifPop(){


		plan_remote.updateTarifPop(plan);
		list_plans= plan_remote.getAllTarifPop();
		
	}
	
	


	public List<TarifsRoaPop> getList_plans() {
		return list_plans;
	}
	public TarifPopRoamRemote getPlan_remote() {
		return plan_remote;
	}


	public void setList_plans(List<TarifsRoaPop> list_plans) {
		this.list_plans = list_plans;
	}



public TarifsRoaPop getTarifPop() {
	return tarifPop;
}
public void setTarifPop(TarifsRoaPop tarifPop) {
	this.tarifPop = tarifPop;
}


	
	

}
