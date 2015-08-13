package com.reporting.mbeans.roamingin;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.reporting.metier.entities.Pay;
import com.reporting.metier.entities.RoamingOperator;
import com.reporting.metier.interfaces.OperateurRoamingRemote;
import com.reporting.metier.interfaces.PaysRemote;


@ManagedBean(name="manage_roaming_operator")
@ViewScoped
public class ManageOperatorMbean {

	
	@EJB
	private OperateurRoamingRemote operator_service;
	
	@EJB
	private PaysRemote pays_service;
	
	private List<Pay> lst_pays;
	
	private RoamingOperator operator;
	private RoamingOperator operator1;
	private List<RoamingOperator> lst_operators;
	
	
	@PostConstruct
	public void init(){
		lst_operators= operator_service.getAllOperateurs();
		lst_pays= pays_service.getAllPays();
		
		operator= new RoamingOperator();
		operator1 = new RoamingOperator();
	}
	
	public void addOperator(){
		operator_service.addOperator(operator);
		lst_operators= operator_service.getAllOperateurs();
	}
	
	public void deleteOperator(){
		operator_service.deleteOperator(operator1);
		lst_operators= operator_service.getAllOperateurs();
	}
	
	public void updateOperator(){
		operator_service.updateOperator(operator1);
		lst_operators = operator_service.getAllOperateurs();
	}
	
	
	public List<RoamingOperator> getLst_operators() {
		return lst_operators;
	}
	public List<Pay> getLst_pays() {
		return lst_pays;
	}
	public RoamingOperator getOperator() {
		return operator;
	}
	public RoamingOperator getOperator1() {
		return operator1;
	}
	public void setLst_operators(List<RoamingOperator> lst_operators) {
		this.lst_operators = lst_operators;
	}
	public void setLst_pays(List<Pay> lst_pays) {
		this.lst_pays = lst_pays;
	}
	public void setOperator(RoamingOperator operator) {
		this.operator = operator;
	}
	public void setOperator1(RoamingOperator operator1) {
		this.operator1 = operator1;
	}
	
}
