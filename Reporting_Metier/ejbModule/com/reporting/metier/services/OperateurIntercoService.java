package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.OperateurInterco;
import com.reporting.metier.interfaces.OperateurIntercoRemote;


@Stateless
public class OperateurIntercoService implements OperateurIntercoRemote {

	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<OperateurInterco> getAllOperateurs(String where) {
		// TODO Auto-generated method stub
		return em.createQuery("Select o from OperateurInterco o where type = '"+where+"'").getResultList();
	}


	@Override
	public void addOperateur(OperateurInterco op) {
		// TODO Auto-generated method stub
		em.persist(op);
		
	}

	@Override
	public void UpdateOperateur(OperateurInterco op) {
		// TODO Auto-generated method stub
		em.merge(op);
	}

	@Override
	public void DeleteOperateur(OperateurInterco op) {
		// TODO Auto-generated method stub
		em.remove(em.contains(op) ? op : em.merge(op));
		
	}
	
	
}
