package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.StatLcr;
import com.reporting.metier.interfaces.StatLcrRemote;


@Stateless
public class StatLcrService implements StatLcrRemote {
	
	
	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<StatLcr> getAllStatLcr() {
		// TODO Auto-generated method stub
		return em.createQuery("Select s From StatLcr s ").getResultList();
	}

	
	
	
}
