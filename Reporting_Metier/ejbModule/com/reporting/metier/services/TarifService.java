package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.Tarif;
import com.reporting.metier.interfaces.TarifRemote;


@Stateless
public class TarifService implements TarifRemote {

	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Tarif> getAllTarifs() {
		// TODO Auto-generated method stub
		return em.createQuery("Select s from Tarif s").getResultList();
	}

	@Override
	public void addTarif(Tarif u) {
		em.persist(u);
		
	}

	@Override
	public void deleteTarif(Tarif u) {
		em.remove(em.contains(u) ? u : em.merge(u));
		
	}

	@Override
	public void updateTarif(Tarif u) {
		em.merge(u);
		
	}
}
