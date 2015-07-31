package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import com.reporting.metier.entities.Trunck;

import com.reporting.metier.interfaces.TrunckRemote;


@Stateless
public class TrunckService implements TrunckRemote {

	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Trunck> getAllTruncks() {
		// TODO Auto-generated method stub
		return em.createQuery("Select o from Trunck o").getResultList();
	}
	
	@Override
	public void addTrunck(Trunck t) {
		// TODO Auto-generated method stub
		em.persist(t);
	}

	@Override
	public void DeleteTrunck(Trunck t) {
		// TODO Auto-generated method stub
		em.remove(em.contains(t) ? t : em.merge(t));
		
	}

	@Override
	public void UpdateTrunck(Trunck t) {
		em.merge(t);
		
	}
	
	
}
