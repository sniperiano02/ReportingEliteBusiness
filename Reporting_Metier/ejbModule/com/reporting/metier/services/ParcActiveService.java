package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.interfaces.ParcActiveRemote;
@Stateless
public class ParcActiveService implements ParcActiveRemote {

	
	@PersistenceContext
	EntityManager em;
	@Override
	public List<Object[]> getStatParcActive(String date,List<String> Where) {
		
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		// TODO Auto-generated method stub
		return em.createQuery("Select "+date+", SUM(nbActive),SUM(parcPrp) From StatParcActPrp  Where "+where+" Group By "+date).getResultList();
	}

}
