package com.reporting.metier.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.reporting.metier.entities.AlarmsServ;
import com.reporting.metier.entities.Alertpro;
import com.reporting.metier.entities.GuiGroup;
import com.reporting.metier.entities.Serveur;


@Remote
public interface alarmeServeurRemote {

	
	public List<AlarmsServ> getAllAlarmesServeurs();
	//public List<Alertpro> getAllAlertPro();
	
	
	public void addAlarme(AlarmsServ g);
	public void deleteAlarme(AlarmsServ g);
	public void updateAlarme(AlarmsServ g);
	public List<Serveur> getAllServeurs();
	public Serveur getServeurByHost(String name);
	
	
	
	public List<Alertpro> getAllAlertpro();
	//public List<Alertpro> getAllAlertPro();
	
	
	public void addAlertpro(Alertpro g);
	public void deleteAlertpro(Alertpro g);
	public void updateAlertpro(Alertpro g);
}
