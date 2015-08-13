package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.Cell;
import com.reporting.metier.entities.Location;
import com.reporting.metier.entities.ParcGptoAct;
import com.reporting.metier.interfaces.LocationRemote;



@Stateless
public class LocationService implements LocationRemote {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Location> getFraudeurLocations(String fraudeur) {
		// TODO Auto-generated method stub
		return em.createQuery("Select l from Location l where l.fraudeur.id ='"+fraudeur+"'").getResultList();
	}

	
	
	
	

}
