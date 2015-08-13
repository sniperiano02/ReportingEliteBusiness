package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.Tarif;
import com.reporting.metier.entities.TarifsRoaIn;
import com.reporting.metier.entities.TarifsRoaOut;
import com.reporting.metier.interfaces.TarifRemote;
import com.reporting.metier.interfaces.TarifRoaINRemote;
import com.reporting.metier.interfaces.TarifRoaOutRemote;


@Stateless
public class TarifRoaINService implements TarifRoaINRemote {

	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<TarifsRoaIn> getAllTarifsRoaOut() {
		// TODO Auto-generated method stub
		return em.createQuery("Select s from TarifsRoaIn s").getResultList();
	}

	@Override
	public void addTarifRoaOut(TarifsRoaIn u) {
		em.persist(u);
		
	}

	@Override
	public void deleteTarifRoaOut(TarifsRoaIn u) {
		em.remove(em.contains(u) ? u : em.merge(u));
		
	}

	@Override
	public void updateTarifRoaOut(TarifsRoaIn u) {
		em.merge(u);
		
	}
}
