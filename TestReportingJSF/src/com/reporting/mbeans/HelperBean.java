package com.reporting.mbeans;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.reporting.metier.entities.Destination;
import com.reporting.metier.entities.GuiGroup;
import com.reporting.metier.entities.GuiTabsGroup;
import com.reporting.metier.entities.GuiUser;
import com.reporting.metier.entities.PlanTarifaire;
import com.reporting.metier.entities.Serveur;
import com.reporting.metier.interfaces.DestinationRemote;
import com.reporting.metier.interfaces.GroupRemote;
import com.reporting.metier.interfaces.PlanTarifaireRemote;
import com.reporting.metier.interfaces.alarmeServeurRemote;
import com.reporting.metier.interfaces.userRemote;

@ManagedBean
@ApplicationScoped
public class HelperBean {

	@EJB
	private userRemote userRemote;
	@EJB
	private GroupRemote groupRemote;
	@EJB
	private alarmeServeurRemote serveurService;

	@EJB
	private DestinationRemote destination_service;
	
	public HelperBean() {
	}

	
	public Serveur findServeurByName(String name) {
		return serveurService.getServeurByHost(name);
	}

	public GuiGroup findGroupByName(String name) {
		return userRemote.getGroupByName(name);
	}

	public GuiTabsGroup findTabByName(String name) {
		return groupRemote.getTabByName(name);
	}

	public Destination findDestinationByName(String name) {
		return destination_service.getDestinationByName(name);
	}

	
}