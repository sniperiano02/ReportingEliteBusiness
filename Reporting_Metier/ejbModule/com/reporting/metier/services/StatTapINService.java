package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.reporting.metier.entities.StatTapout;
import com.reporting.metier.interfaces.StatTapINRemote;
import com.reporting.metier.interfaces.StatTapOutRemote;


@Stateless
public class StatTapINService implements StatTapINRemote {

	
	@PersistenceContext
	EntityManager em;
	@Override
	public List<Object[]> getStatTapOutByFilters(String x, String y, List<String> Where) {
		// TODO Auto-generated method stub
		
			String where =  Where.get(0);
			if(Where.size()>=2){
				for(int i=1;i<Where.size();i++){
					where = where+" AND "+ Where.get(i);
				}
			}
			
			
				Query q=  em.createQuery("Select "+x+","+y+" From StatTapin t   Where  "+where+" Group By "+x+" Order By "+x );
				
				return q.getResultList();
	}
	@Override
	public List<Integer> getAllYears() {
		// TODO Auto-generated method stub
		return em.createQuery("Select  distinct extract(year from to_date(dateAppel,'YYMMDD'))From StatTapin  order by extract(year from to_date(dateAppel,'YYMMDD')) ").getResultList();
	}
	@Override
	public List<Object[]> getAllStatTarifMoy(String x,List<String> Where) {
		
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		

		Query q = em.createQuery("Select "+x+" ,CASE WHEN sum(dureeTotal) <> 0 THEN ROUND((sum(chargeTotal) / (sum(dureeTotal))*60),3) ELSE 0 END,SUM(nombreTotal),SUM(dureeTotal),SUM(chargeTotal) From StatTapin s where  "+where+"  Group By "+x+" Order By "+x+" ASC");

				
			List<Object[]> resultList = q.getResultList();
			return resultList;
		
	}
	public List<String> getTypeCall() {
		// TODO Auto-generated method stub
		return em.createQuery("Select distinct typeCall from StatTapin s").getResultList();
	}


}
