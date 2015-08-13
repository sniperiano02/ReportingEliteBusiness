package com.reporting.metier.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import com.reporting.metier.interfaces.StatReconCtiMscRemote;



@Stateless
public class StatReconCtiMscimpl implements StatReconCtiMscRemote {

	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Object[]> getAllStatRecon(String x, String y,
			String action, String groupby, List<String> Where) {
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		
		Query q = em.createQuery("Select "+x+" ,"+action+" "+y+" " +"From StatReconCtiMsc s"+" Where "+where+" "+groupby+" Order By  "+x+" DESC");
		
			List<Object[]> resultList = q.getResultList();
			
				return resultList;	
			
	}

	@Override
	public List<Object[]> getAllStatReconCtiMsc(String x,String where) {
		// TODO Auto-generated method stub
		if(x.contains("to_char")){
			return em.createQuery("Select "+x+" ,SUM(nbAppelCti),SUM(dureeAppelCti),SUM(nbAppelMsc),SUM(dureeAppelMsc),SUM(nbRoaOut),SUM(dureeRoaOut),SUM(dureeAppelMsc)+(SUM(dureeRoaOut)-SUM(dureeAppelCti)),SUM(dureeAppelMsc)+SUM(dureeRoaOut) From StatReconCtiMsc s where  "+where+"  Group By to_date(dateAppel,'YYMMDD') Order By to_date(dateAppel,'YYMMDD') ASC ").getResultList();
			
		}else{
			return em.createQuery("Select "+x+" ,SUM(nbAppelCti),SUM(dureeAppelCti),SUM(nbAppelMsc),SUM(dureeAppelMsc),SUM(nbRoaOut),SUM(dureeRoaOut),SUM(dureeAppelMsc)+(SUM(dureeRoaOut)-SUM(dureeAppelCti)),SUM(dureeAppelMsc)+SUM(dureeRoaOut)  From StatReconCtiMsc s where   "+where+"  Group By "+x+" Order BY "+x+" ASC").getResultList();
			
		}
		}
	

	@Override
	public Map<Object, Number> getMscBytranche(String x, String y,
			String action, String groupby, List<String> Where) {
		// TODO Auto-generated method stub
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		
		Query q = em.createQuery("Select CAST(trancheHoraire AS integer) ,"+action+" "+y+" " +"From StatMsc s"+" Where "+where+" "+"Group By CAST(trancheHoraire AS integer) Order by  CAST(trancheHoraire AS integer) DESC ");
		List<Object[]> resultList = q.getResultList();
		Map<Object, Number> resultMap = new HashMap<Object, Number>(resultList.size());
		for (Object[] result : resultList){
		  
			
				resultMap.put(result[0], (Number)result[1]);
			
		} 
		return resultMap;	
	
	}

	@Override
	public List<Integer> getAllYears() {
		// TODO Auto-generated method stub
		return em.createQuery("Select distinct extract(year from to_date(dateAppel,'YYMMDD'))From StatReconCtiMsc s order by extract(year from to_date(dateAppel,'YYMMDD')) ").getResultList();
	}

	@Override
	public List<Object[]> getDetailsStatReconCtiMsc(String x,String y, String where) {
		// TODO Auto-generated method stub
		return em.createQuery("Select "+x+" "+y+" From StatReconCtiMsc s where   "+where+"  Group By "+x+" Order By "+x).getResultList();
		
	}

	@Override
	public List<Object[]> getDetailsDestinationStatReconCtiMsc(String x,
			String where) {
		// TODO Auto-generated method stub
		return em.createQuery("Select "+x+", s.destination.nomDestination ,SUM(dureeAppelCti),SUM(dureeAppelMsc),SUM(dureeRoaOut),SUM(nbAppelCti),SUM(nbAppelMsc),SUM(nbRoaOut),SUM(dureeAppelCti)-SUM(dureeAppelMsc) From StatReconCtiMsc s where "+where+" Group BY  s.destination.nomDestination, "+x ).getResultList();
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
		
		Query q = em.createQuery("Select "+x+" , "+y+" From StatReconMscIn s"+" Where "+where+" Group By to_date(dateAppel,'YYMMDD') Order By to_date(dateAppel,'YYMMDD') ASC");
		
			List<Object[]> resultList = q.getResultList();
		
			return resultList;
			
		
	}
	
	
	
}
