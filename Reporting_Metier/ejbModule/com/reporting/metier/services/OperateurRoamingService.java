package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.RoamingOperator;
import com.reporting.metier.interfaces.OperateurRoamingRemote;


@Stateless
public class OperateurRoamingService implements OperateurRoamingRemote  {

	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<RoamingOperator> getAllOperateurs() {
		// TODO Auto-generated method stub
		return em.createQuery("Select o From RoamingOperator o").getResultList();
	}

	@Override
	public void addOperator(RoamingOperator or) {
		// TODO Auto-generated method stu
		em.persist(or);
		
	}

	@Override
	public void deleteOperator(RoamingOperator or) {
		// TODO Auto-generated method stub
		em.remove(em.contains(or) ? or : em.merge(or));
	}

	@Override
	public void updateOperator(RoamingOperator or) {
		// TODO Auto-generated method stub
		em.merge(or);
	}
	
	
	
}
