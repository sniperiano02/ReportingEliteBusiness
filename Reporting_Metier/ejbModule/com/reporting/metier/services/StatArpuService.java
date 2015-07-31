package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.reporting.metier.entities.StatArpuPrp;
import com.reporting.metier.interfaces.StatArpuRemote;


@Stateless
public class StatArpuService implements StatArpuRemote {

	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Object[]> getStatArpu(String x, String list_y, String groupby,
			List<String> Where) {
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		
		
			Query q=  em.createQuery("Select "+x+","+list_y+" From StatArpuPrp  Where  "+where+" Group By "+x+" Order By "+x );
			
			return q.getResultList();
	}

	@Override
	public List<Object[]> getDetailsStatArpu(String Where) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getAllYears() {
		// TODO Auto-generated method stub
		return em.createQuery("Select  distinct extract(year from to_date(dateAppel,'YYMMDD'))From StatArpuPrp  order by extract(year from to_date(dateAppel,'YYMMDD')) ").getResultList();
	}
	
	
	
}
