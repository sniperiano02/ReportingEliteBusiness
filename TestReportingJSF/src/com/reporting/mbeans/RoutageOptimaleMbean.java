package com.reporting.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.reporting.metier.entities.StatLcr;
import com.reporting.metier.interfaces.StatLcrRemote;



@ManagedBean(name="routage")
@ViewScoped
public class RoutageOptimaleMbean {

	
	
	@EJB
	private StatLcrRemote statlcr_service;
	
	private List<StatLcr> listeroutage = new ArrayList<>();
	
	
	public List<StatLcr> getListeroutage() {
		return listeroutage;
	}
	public void setListeroutage(List<StatLcr> listeroutage) {
		this.listeroutage = listeroutage;
	}
	
	@PostConstruct
	public void init(){
		
		listeroutage =statlcr_service.getAllStatLcr();
	}
}
