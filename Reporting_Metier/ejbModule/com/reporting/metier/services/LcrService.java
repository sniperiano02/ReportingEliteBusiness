package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.TarifsInter;
import com.reporting.metier.entities.TarifsInterTmp;
import com.reporting.metier.interfaces.TarifinterLcrRemote;

@Stateless
public class LcrService implements TarifinterLcrRemote {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<TarifsInterTmp> getTarifTempByFichier(String fichier_name) {
		// TODO Auto-generated method stub
		return em.createQuery("Select t from TarifsInterTmp t where t.nomFichier= '"+fichier_name+"'").getResultList();
		
	}
	@Override
	public List<TarifsInter> getTarifsInterByFichier(String fichier_name) {
		// TODO Auto-generated method stub
		return em.createQuery("Select t from TarifsInter t where t.nomFichier= '"+fichier_name+"'").getResultList();
		
	}
}
