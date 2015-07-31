package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.annotations.Filters;

import com.reporting.metier.entities.CategoriesFraude;
import com.reporting.metier.entities.FiltresFlux;
import com.reporting.metier.entities.FiltresFraude;
import com.reporting.metier.entities.FluxCdr;
import com.reporting.metier.entities.ParametresCategory;
import com.reporting.metier.entities.ParametresFraude;
import com.reporting.metier.interfaces.FraudeManagerRemote;


@Stateless
public class FraudeManagerService implements FraudeManagerRemote {

	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<CategoriesFraude> getAllCategories() {
		// TODO Auto-generated method stub
		
		return em.createQuery("Select c From CategoriesFraude c").getResultList();
	}

	@Override
	public List<FluxCdr> getAllFlux() {
		// TODO Auto-generated method stub
		return em.createQuery("Select f From FluxCdr f").getResultList();
	}

	@Override
	public List<ParametresFraude> getParametresFraude(String Where) {
		// TODO Auto-generated method stub
		
		return em.createQuery("Select Distinct p.parametre From ParametresCategory p where "+Where).getResultList();
	}

	@Override
	public List<FiltresFraude> getFilterFiltresFraude(String where) {
		// TODO Auto-generated method stub
		
		return em.createQuery("Select Distinct f.filtre From FiltresFlux f where "+where).getResultList();
	}
	
	
}
