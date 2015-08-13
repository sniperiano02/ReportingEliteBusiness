package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.Tarif;
import com.reporting.metier.entities.TarifsRoaOut;
import com.reporting.metier.interfaces.TarifRemote;
import com.reporting.metier.interfaces.TarifRoaOutRemote;


@Stateless
public class TarifRoaOutService implements TarifRoaOutRemote {

	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<TarifsRoaOut> getAllTarifsRoaOut() {
		// TODO Auto-generated method stub
		return em.createQuery("Select s from TarifsRoaOut s").getResultList();
	}

	@Override
	public void addTarifRoaOut(TarifsRoaOut u) {
		em.persist(u);
		
	}

	@Override
	public void deleteTarifRoaOut(TarifsRoaOut u) {
		em.remove(em.contains(u) ? u : em.merge(u));
		
	}

	@Override
	public void updateTarifRoaOut(TarifsRoaOut u) {
		em.merge(u);
		
	}
}
