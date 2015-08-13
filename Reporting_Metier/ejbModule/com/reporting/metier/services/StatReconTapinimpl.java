package com.reporting.metier.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.reporting.metier.interfaces.StatReconTapinRemote;







@Stateless
public class StatReconTapinimpl implements StatReconTapinRemote {

	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Object[]> getAllStatReconTapin(String x, String y,
			String action, String groupby, List<String> Where) {
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		
		Query q = em.createQuery("Select "+x+" ,"+action+" "+y+" " +"From StatRecTapin s"+" Where "+where+" "+groupby+" Order By  "+x+" DESC");
		
			List<Object[]> resultList = q.getResultList();
			
				return resultList;	
			
	}

	@Override
	public List<Object[]> getAllStatReconTapin(String x,String where) {
		// TODO Auto-generated method stub
		if(x.contains("to_char")){
			return em.createQuery("Select "+x+" ,SUM(nbPrepaye),SUM(dureePrepaye),SUM(nbTapin),SUM(dureeTapin),SUM(nbPop),SUM(dureePop),SUM(revenuePop),SUM(charge),SUM(revenuePrepaye) From StatRecTapin s where  "+where+"  Group By to_date(dateAppel,'YYMMDD') Order By to_date(dateAppel,'YYMMDD') ASC ").getResultList();
			
		}else{
			return em.createQuery("Select "+x+" ,SUM(nbPrepaye),SUM(dureePrepaye),SUM(nbTapin),SUM(dureeTapin),SUM(nbPop),SUM(dureePop),SUM(revenuePop),SUM(charge),SUM(revenuePrepaye)   From StatRecTapin s where   "+where+"  Group By "+x+" Order BY "+x+" ASC").getResultList();
			
		}
		}
	

	

	@Override
	public List<Integer> getAllYears() {
		// TODO Auto-generated method stub
		return em.createQuery("Select distinct extract(year from to_date(dateAppel,'YYMMDD'))From StatRecTapin s order by extract(year from to_date(dateAppel,'YYMMDD')) ").getResultList();
	}

	@Override
	public List<Object[]> getDetailsStatReconTapin(String x,String y, String where) {
		// TODO Auto-generated method stub
		return em.createQuery("Select "+x+" "+y+" From StatRecTapin s where   "+where+"  Group By "+x+" Order By "+x).getResultList();
		
	}

	@Override
	public List<Object[]> getDetailsDestinationStatReconTapin(String x,
			String where) {
		// TODO Auto-generated method stub
		return em.createQuery("Select "+x+", operateur.operateur,SUM(nbPrepaye),SUM(dureePrepaye),SUM(revenuePrepaye),SUM(nbTapin),SUM(dureeTapin),SUM(charge),SUM(nbPop),SUM(dureePop),SUM(revenuePop) From StatRecTapin s where "+where+" Group BY operateur.operateur, "+x ).getResultList();
	}

	@Override
	public List<Object[]> getAllStatReconCDRIN(String x,String y, List<String> Where) {
		// TODO Auto-generated method stub
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		
		Query q = em.createQuery("Select "+x+" , "+y+" From StatRecTapin s"+" Where "+where+" Group By to_date(dateAppel,'YYMMDD') Order By to_date(dateAppel,'YYMMDD') ASC");
		
			List<Object[]> resultList = q.getResultList();
		
			return resultList;
			
		
	}
	
	
	
}
