package com.reporting.metier.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.reporting.metier.entities.StatRecharge;
import com.reporting.metier.interfaces.StatRechargeRemote;


@Stateless
public class StatRechargeService implements StatRechargeRemote {

	
	@PersistenceContext
	EntityManager em;
	@Override
	public List<Object[]> getAllStatRecharge(String x, String y,
			List<String> Where) {
		// TODO Auto-generated method stub
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		List<Object[]> res= new ArrayList<Object[]>();
		if(x.contains("substring")){
		
			Query q=  em.createQuery("Select "+x+","+y+"From StatRecharge s Where  "+where+" Group By trancheHoraire Order By CAST (trancheHoraire as integer)" );
			res=q.getResultList();
			if(!res.isEmpty()){
				return res;
			}else
				return null;
		

		}else{
			Query q=  em.createQuery("Select "+x+","+y+"From StatRecharge s Where  "+where+" Group By "+x+" Order By "+x );
			res=q.getResultList();
			if(!res.isEmpty()){
				return res;
			}else
				return new ArrayList<Object[]>();
		
			

		}
		
		
	}

	@Override
	public List<Integer> getAllYears() {
		// TODO Auto-generated method stub
		return em.createQuery("Select distinct extract(year from to_date(dateAppel,'YYMMDD'))From StatRecharge s order by extract(year from to_date(dateAppel,'YYMMDD')) ").getResultList();
	}

	@Override
	public List<Object[]> getStatRechargeByType(List<String> Where) {
		// TODO Auto-generated method stub
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		Query q = 	em.createNativeQuery("Select 'Recharge' as recharge, sum(recharge_total) as sum_recharge  FROM stat.stat_recharge   where stat_recharge.type_recharge like '%recharge%' and  "+where+" UNION Select 'Bonus' as bonus ,sum(recharge_total) as sum_recharge  From stat.stat_recharge  where stat_recharge.type_recharge not like '%recharge%' and  "+where);

				
		
		List<Object[]> resultList = q.getResultList();
			return resultList;
		
	}

}
