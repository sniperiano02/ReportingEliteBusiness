package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reporting.metier.entities.StatVerifOffreIn;
import com.reporting.metier.interfaces.VerifOffreIN;

@Stateless
public class VerifOffreINService implements VerifOffreIN {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Object[]> getStatVerifOffre(String x,String y, List<String> Where,String group) {
		
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		// TODO Auto-generated method stub
		return em.createQuery("Select  "+x+" ,"+y+" From StatVerifOffreIn  where "+where+" Group By "+group).getResultList();
	}

	@Override
	public List<Integer> getAllYears() {
		// TODO Auto-generated method stub
		return em.createQuery("Select  distinct extract(year from to_date(dateAppel,'YYMMDD'))From StatVerifOffreIn s order by extract(year from to_date(dateAppel,'YYMMDD')) ").getResultList();
		}
	
	
	
	
	
	
	
}
