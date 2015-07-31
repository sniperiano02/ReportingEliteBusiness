package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.StatErreur;
import com.reporting.metier.interfaces.StatErreurRemote;


@Stateless
public class StatErreurService implements StatErreurRemote {

	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<StatErreur> getStatsErreur(String where) {
		// TODO Auto-generated method stub
		return em.createQuery("Select s From StatErreur s where s.codeAnomalie = '"+where+"'").getResultList();
	}
}
