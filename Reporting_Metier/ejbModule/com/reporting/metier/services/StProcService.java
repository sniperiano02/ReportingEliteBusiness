package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.Proccontrole;
import com.reporting.metier.entities.Stproc;
import com.reporting.metier.interfaces.StProcRemote;


@Stateless
public class StProcService implements StProcRemote {

	
	@PersistenceContext
	EntityManager em;
	@Override
	public List<Stproc> getAllStProc() {
		// TODO Auto-generated method stub
		return em.createQuery("Select p from Stproc p").getResultList();
	}

	@Override
	public void arretProc(Stproc proc) {
		// TODO Auto-generated method stub
		em.merge(proc);
		
	}

	@Override
	public void activerProc(Stproc proc) {
		// TODO Auto-generated method stub
		em.merge(proc);
		
	}

	@Override
	public void changerCycle(Stproc proc) {
		// TODO Auto-generated method stub
		em.merge(proc);
		
	}

	@Override
	public List<Proccontrole> getDetailsByName(String name) {
		// TODO Auto-generated method stub
		return em.createQuery("Select p from Proccontrole p where proc = '"+name+"'").getResultList();
	}

}
