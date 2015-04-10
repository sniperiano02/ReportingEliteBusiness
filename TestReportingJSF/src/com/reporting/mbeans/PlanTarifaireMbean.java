package com.reporting.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.reporting.metier.entities.PlanTarifaire;
import com.reporting.metier.interfaces.PlanTarifaireRemote;


@ManagedBean(name="plan_tarif")
@ViewScoped
public class PlanTarifaireMbean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3488417247629137575L;
	
	
	@EJB
	private PlanTarifaireRemote plan_remote;
	
	private PlanTarifaire plan ;
	
	private String code;
	
	private List<PlanTarifaire> list_plans = new ArrayList<PlanTarifaire>();
	
	public PlanTarifaireMbean() {
		// TODO Auto-generated constructor stub
	}
	@PostConstruct
	public void init(){
		plan = new PlanTarifaire();
		list_plans= plan_remote.getAllPlanTarifaires();
	}
	
	
	public void createPlan(){
//	PlanTarifaire p = new PlanTarifaire();
plan.setCodePlanTarifaire("1234567");

		plan_remote.createPlanT(plan);
	}
	
	


	public List<PlanTarifaire> getList_plans() {
		return list_plans;
	}
	public PlanTarifaireRemote getPlan_remote() {
		return plan_remote;
	}


	public void setList_plans(List<PlanTarifaire> list_plans) {
		this.list_plans = list_plans;
	}




	public PlanTarifaire getPlan() {
		return plan;
	}




	public void setPlan(PlanTarifaire plan) {
		this.plan = plan;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}
	
	

}
