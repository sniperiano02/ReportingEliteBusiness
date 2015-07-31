package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.Pay;
import com.reporting.metier.interfaces.PaysRemote;


@Stateless
public class PaysService implements PaysRemote {

	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Pay> getAllPays() {
		// TODO Auto-generated method stub
		return em.createQuery("Select p From Pay p").getResultList();
	}

	@Override
	public void addPays(Pay p) {
		// TODO Auto-generated method stub
		em.persist(p);
	}

	@Override
	public void DeletePays(Pay p) {
		// TODO Auto-generated method stub
		em.remove(em.contains(p) ? p : em.merge(p));
		
	}

	@Override
	public void UpdatePays(Pay p) {
		em.merge(p);
		
	}
}
