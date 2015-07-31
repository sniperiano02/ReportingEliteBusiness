package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.IntervalHeure;
import com.reporting.metier.interfaces.IntervalleRemote;


@Stateless
public class IntervalleService implements IntervalleRemote {

	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<IntervalHeure> getAllIntervalHeures() {
		// TODO Auto-generated method stub
		return em.createQuery("Select i From IntervalHeure i ").getResultList();
	}

	@Override
	public void addIntervalle(IntervalHeure ih) {
		// TODO Auto-generated method stub
		em.persist(ih);
		
	}

	@Override
	public void DeleteIntervalle(IntervalHeure ih) {
		// TODO Auto-generated method stub
		em.remove(em.contains(ih) ? ih : em.merge(ih));
	}

	@Override
	public void UpdateIntervalle(IntervalHeure ih) {
		// TODO Auto-generated method stub
		em.merge(ih);
	}
}
