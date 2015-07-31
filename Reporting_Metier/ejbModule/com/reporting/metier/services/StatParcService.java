package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.reporting.metier.interfaces.StatParcRemote;


@Stateless
public class StatParcService implements StatParcRemote {

	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Object[]> getEvolutionParc(String x, String list_y,
			String groupby, List<String> Where) {
		// TODO Auto-generated method stub
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		
		
			Query q=  em.createQuery("Select "+x+","+list_y+" From StatParc  Where  "+where+" Group By "+x+" Order By "+x );
			
			return q.getResultList();
	}
	
	
	
}
