package com.reporting.metier.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.reporting.metier.entities.Destination;
import com.reporting.metier.entities.Destination;
import com.reporting.metier.entities.DetailsDestination;
import com.reporting.metier.entities.ZonesDestination;
import com.reporting.metier.interfaces.DestinationRemote;



@Stateless
public class DestinationService implements DestinationRemote{

	
	@PersistenceContext
	EntityManager em;
	@Override
	public List<Destination> getAllDestination() {
		// TODO Auto-generated method stub
		return em.createQuery("Select d  From Destination d").getResultList();
	}

	@Override
	public void addDestination(Destination dest) {
		em.persist(dest);
		
	}

	@Override
	public void deleteDestination(Destination i) {
		em.remove(em.contains(i) ? i : em.merge(i));
		
	}

	@Override
	public void updateDestination(Destination dest) {
	em.merge(dest);
	}

	@Override
	public Destination getDestinationByName(String name) {
		Destination found = null;
		Query query = em.createQuery("select g from Destination g where g.dest=:x");
		query.setParameter("x", name);
		try{
			found = (Destination) query.getSingleResult();
		}catch(Exception ex){
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "no Destination with name="+name);
		}
		return found;
	}

	@Override
	public void deleteDetail(DetailsDestination det, Destination dest) {
		// TODO Auto-generated method stub
		em.remove(em.contains(det) ? det : em.merge(det));
		em.merge(dest);
		
	}

	@Override
	public void addDetail(DetailsDestination det, Destination dest) {
		// TODO Auto-generated method stub
		det.setDest(dest);
		em.persist(det);
		
	}


}
