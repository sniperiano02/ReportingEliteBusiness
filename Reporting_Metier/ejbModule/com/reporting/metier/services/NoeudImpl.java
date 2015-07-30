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
	@Override
	public void PersistNoeud(Noeud n) {
		em.persist(n);
		
	}
	@Override
	public void deleteNoeud(Noeud n) {
		em.remove(em.contains(n) ? n : em.merge(n));
		
	}
	@Override
	public void updateNoeud(Noeud n) {
		// TODO Auto-generated method stub
		em.merge(n);
	}

}
