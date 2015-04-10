package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.TypeDestination;
import com.reporting.metier.interfaces.TypeDestinationRemote;


@Stateless
public class TypeDestinationImpl implements TypeDestinationRemote {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<TypeDestination> getAllTypeDest() {
		// TODO Auto-generated method stub
		return em.createQuery("Select t from TypeDestination t").getResultList();
	}
	@Override
	public List<String> getAllStringTypeDest() {
		// TODO Auto-generated method stub
		return em.createQuery("Select distinct typeDest from TypeDestination t").getResultList();
	}

}
