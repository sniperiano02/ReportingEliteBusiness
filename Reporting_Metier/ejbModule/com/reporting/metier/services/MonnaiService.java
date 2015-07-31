package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.Monnaie;
import com.reporting.metier.interfaces.MonnaiRemote;



@Stateless
public class MonnaiService implements MonnaiRemote {

	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Monnaie> getAllMonnaies() {
		// TODO Auto-generated method stub
		return em.createQuery("Select p From Monnaie p").getResultList();
	}

	
}
