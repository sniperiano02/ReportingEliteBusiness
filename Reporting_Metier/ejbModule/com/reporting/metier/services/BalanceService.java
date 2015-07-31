package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.reporting.metier.entities.StatBalanceNeg;
import com.reporting.metier.interfaces.Balance_NegRemote;


@Stateless
public class BalanceService implements Balance_NegRemote {

	
	@PersistenceContext
	EntityManager em;
	@Override
	public List<Integer> getAllYears() {
		// TODO Auto-generated method stub
		return em.createQuery("Select distinct extract(year from to_date(dateAppel,'YYMMDD'))From StatBalanceNeg s order by extract(year from to_date(dateAppel,'YYMMDD')) ").getResultList();
	}
	
	
	@Override
	public List<Object[]> getAllStatBalance(String x, String y,
			String action, String groupby, List<String> Where) {
		// TODO Auto-generated method stub
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		
		
			Query q=  em.createQuery("Select "+x+","+y+"From StatBalanceNeg s Where  "+where+" Group By "+x+" Order By "+x );
			
			return q.getResultList();

		
	}

}
