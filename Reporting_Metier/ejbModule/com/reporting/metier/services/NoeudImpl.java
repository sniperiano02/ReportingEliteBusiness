package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.Noeud;
import com.reporting.metier.interfaces.NoeudRemote;

@Stateless
public class NoeudImpl implements NoeudRemote {

	@PersistenceContext
	private EntityManager em;
	@Override
	public List<Noeud> getNoeudNoms() {
		return em.createQuery("Select s from Noeud s").getResultList();
	}
	@Override
	public List<String> getNoeudCodes() {
		return em.createQuery("Select distinct codeNoeud from Noeud s").getResultList();
	}

}
