package com.reporting.mbeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.reporting.metier.interfaces.StatParcRemote;


@ManagedBean(name="evo_parc")
@ViewScoped
public class EvolutionParcMbean {
	
	
	@EJB
	private StatParcRemote stat_parc_service;
	
	
	

}
