package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.reporting.metier.entities.StatInterBalance;
import com.reporting.metier.interfaces.StatInterBalanceNatRemote;
import com.reporting.metier.interfaces.StatInterBalanceRemote;


@Stateless
public class StatInterBalanceNatService implements StatInterBalanceNatRemote {

	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Object[]> getStatInterBalanceNat(String x, String list_y, String groupby,
			List<String> Where) {
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		
		
			Query q=  em.createQuery("Select "+x+","+list_y+" From StatNatintercoBalance  Where  "+where+" Group By "+x+" Order By "+x );
			
			return q.getResultList();
	}

	@Override
	public List<Object[]> getDetailsStatInterBalanceNat(String Where) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getAllYears() {
		// TODO Auto-generated method stub
		return em.createQuery("Select  distinct extract(year from to_date(dateAppel,'YYMMDD'))From StatNatintercoBalance  order by extract(year from to_date(dateAppel,'YYMMDD')) ").getResultList();
	}
	
	
	
}
