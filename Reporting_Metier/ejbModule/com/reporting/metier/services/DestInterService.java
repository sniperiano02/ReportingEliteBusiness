package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.InterDest;

import com.reporting.metier.interfaces.DestInterRemote;



@Stateless
public class DestInterService implements DestInterRemote {

	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<InterDest> getAllInterDests() {
		// TODO Auto-generated method stub
		return em.createQuery("Select p From InterDest p").getResultList();
	}

	@Override
	public void addInterDest(InterDest i) {
		// TODO Auto-generated method stub
		em.persist(i);
	}

	@Override
	public void DeleteInterDest(InterDest i) {
		// TODO Auto-generated method stub
		em.remove(em.contains(i) ? i : em.merge(i));
		
	}

	@Override
	public void UpdateInterDest(InterDest i) {
		em.merge(i);
		
	}
}
