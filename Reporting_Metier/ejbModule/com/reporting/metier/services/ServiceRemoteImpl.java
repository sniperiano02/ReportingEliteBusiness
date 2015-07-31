package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.Service;
import com.reporting.metier.interfaces.ServiceRemote;


@Stateless
public class ServiceRemoteImpl implements ServiceRemote {
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Service> getAllService() {
		// TODO Auto-generated method stub
		return em.createQuery("Select s from Service s").getResultList();
	}

}
