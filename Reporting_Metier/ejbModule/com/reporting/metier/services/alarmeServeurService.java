package com.reporting.metier.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.reporting.metier.entities.AlarmsServ;
import com.reporting.metier.entities.Alertpro;
import com.reporting.metier.entities.GuiGroup;
import com.reporting.metier.entities.Serveur;
import com.reporting.metier.interfaces.alarmeServeurRemote;


@Stateless
public class alarmeServeurService implements alarmeServeurRemote {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<AlarmsServ> getAllAlarmesServeurs() {
		// TODO Auto-generated method stub
		return em.createQuery("Select s From AlarmsServ s").getResultList();
	}

	@Override
	public List<Serveur> getAllServeurs() {
		// TODO Auto-generated method stub
		return em.createQuery("Select s From Serveur s").getResultList();
	}

	@Override
	public void addAlarme(AlarmsServ g) {
		
		em.persist(g);
		
	}

	@Override
	public void deleteAlarme(AlarmsServ g) {
		em.remove(em.contains(g) ? g : em.merge(g));
		
	}

	@Override
	public void updateAlarme(AlarmsServ g) {
		em.merge(g);
		
	}

	@Override
	public Serveur getServeurByHost(String name) {
		Serveur found = null;
		Query query = em.createQuery("select g from Serveur g where g.hostname=:x");
		query.setParameter("x", name);
		try{
			found = (Serveur) query.getSingleResult();
		}catch(Exception ex){
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "no Serveur with name="+name);
		}
		return found;
	}

	@Override
	public List<Alertpro> getAllAlertpro() {
		// TODO Auto-generated method stub
		return em.createQuery("Select s From Alertpro s").getResultList();
	}

	@Override
	public void addAlertpro(Alertpro g) {
		// TODO Auto-generated method stub
		em.persist(g);
		
	}

	@Override
	public void deleteAlertpro(Alertpro g) {
		// TODO Auto-generated method stub
		em.remove(em.contains(g) ? g : em.merge(g));
	}

	@Override
	public void updateAlertpro(Alertpro g) {
		// TODO Auto-generated method stub
		em.merge(g);
	}
	

}
