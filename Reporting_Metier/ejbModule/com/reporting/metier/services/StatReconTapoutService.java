package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.reporting.metier.entities.StatDetailsReconMscTapout;
import com.reporting.metier.entities.StatOperateurDestReconMscTapout;
import com.reporting.metier.entities.StatReconMscTapout;
import com.reporting.metier.interfaces.StatReconRemote;
import com.reporting.metier.interfaces.StatReconTapoutRemote;
import com.reporting.metier.interfaces.StatTapOutRemote;



@Stateless
public class StatReconTapoutService implements StatReconTapoutRemote {

	
	@PersistenceContext
	EntityManager em;






	@Override
	public List<Object[]> getAllStatReconTapout(String x, String y,
			List<String> Where) {
		// TODO Auto-generated method stub
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		
		Query q = em.createQuery("Select "+x+" , "+y+" " +"From StatReconMscTapout s"+" Where "+where+" Group By "+x+"  Order By  "+x+" DESC");
		
			List<Object[]> resultList = q.getResultList();
			
				return resultList;	
	}






	@Override
	public List<Object[]> getDetailsStatReconTapout(String x, String y,
			String where) {
		
		// TODO Auto-generated method stub
	Query q =	em.createQuery("Select "+x+" , "+y+" " +"From StatDetailsReconMscTapout s"+" Where "+where+" Group By "+x+"  Order By  "+x+" DESC");
		
		List<Object[]> resultList = q.getResultList();
		
			return resultList;
	}






	@Override
	public List<Object[]> getDetailsOperateurStatReconTapout(String x,
			String where) {
		
		// TODO Auto-generated method stub
Query q =	em.createQuery("Select "+x+", SUM(nbAnomalie),SUM(dureeAnomalie),SUM(nbTotalMsc),SUM(dureeTotalMsc),SUM(nbTotalTap),SUM(dureeTotalTap),SUM(kpiNb),SUM(kpiDuree),SUM(impact) From StatOperateurDestReconMscTapout s"+" Where "+where+" Group By "+x+"  Order By  "+x+" DESC");
		
		List<Object[]> resultList = q.getResultList();
		
			return resultList;
	}


	
	
}
