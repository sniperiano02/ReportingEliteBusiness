package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.Monnaie;
import com.reporting.metier.entities.TauxChange;
import com.reporting.metier.interfaces.TauxChangeRemote;


@Stateless
public class TauxChangeService implements TauxChangeRemote {

	
	@PersistenceContext
	EntityManager em;
	@Override
	public List<TauxChange> getAllTaux() {
		// TODO Auto-generated method stub
		
		return em.createQuery("Select t From TauxChange t ").getResultList();
	}

	@Override
	public void addTaux(TauxChange t) {
		// TODO Auto-generated method stub
		em.persist(t);
	}

	@Override
	public void updateTaux(TauxChange t) {
		// TODO Auto-generated method stub
		em.merge(t);
	}

	@Override
	public void deleteTaux(TauxChange t) {
		// TODO Auto-generated method stub
		em.remove(em.contains(t) ? t : em.merge(t));
	}

	@Override
	public List<Monnaie> getAllMonnaie() {
		// TODO Auto-generated method stub
		return em.createQuery("Select m from Monnaie m").getResultList();
	}

	
}
