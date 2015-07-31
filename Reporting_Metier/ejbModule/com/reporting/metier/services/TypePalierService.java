package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.TypePalier;
import com.reporting.metier.interfaces.TypePalierRemote;


@Stateless
public class TypePalierService implements TypePalierRemote {

	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<TypePalier> getAllTypePaliers() {
		// TODO Auto-generated method stub
		return em.createQuery("Select t from TypePalier t ").getResultList();
	}
	
	
	
}
