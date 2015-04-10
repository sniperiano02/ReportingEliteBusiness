package com.reporting.metier.services;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;





import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.reporting.metier.entities.StatMsc;
import com.reporting.metier.interfaces.StatMscImplRemote;

/**
 * Session Bean implementation class StatMscImpl
 */
@Stateless
public class StatMscImpl implements StatMscImplRemote {

	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public StatMscImpl() {
        // TODO Auto-generated constructor stub
    }
  






	@Override
	public Map<Object, Number> getConfiguartionChartByCfg(String x, String y,String action ,String groupby) {
		
			if(x.equals("dateAppel")){
				
				Query q = em.createQuery("Select  to_date("+x+",'yyMMdd')  ,"+action+" "+y+" " +"From StatMsc s"+" "+groupby+" Order BY to_date(s.dateAppel,'YYMMDD') desc ");
				List<Object[]> resultList = q.getResultList().subList(0, 7);
				Map<Object, Number> resultMap = new HashMap<Object, Number>(resultList.size());
				for (Object[] result : resultList){
				  
					
						resultMap.put( result[0].toString(), (Number)result[1]);
					
				} 
				return resultMap;	
			}else if(x.equals("typeDest")){
				Query q = em.createQuery("Select s.destination.typeDest," +action+" "+y+" " +"From StatMsc s   Group By s.destination.typeDest");
				List<Object[]> resultList = q.getResultList();
				System.out.println(resultList.size());
				Map<Object, Number> resultMap = new HashMap<Object, Number>(resultList.size());
				for (Object[] result : resultList){
						resultMap.put(result[0].toString(), (Number)result[1]);
				} 
				return resultMap;
			}else{
				
				Query q = em.createQuery("Select "+x+" ,"+action+" "+y+" " +"From StatMsc s"+" "+groupby);
				List<Object[]> resultList = q.getResultList();
				Map<Object, Number> resultMap = new HashMap<Object, Number>(resultList.size());
				for (Object[] result : resultList){
					if(result[0]!=null && result[1]!=null){
						resultMap.put(result[0].toString(), (Number)result[1]);
					}
					
						
					
				} 
				return resultMap;	
			}
		
		
	}
	



	@Override
	public Map<String, Number> getConfiguartionChartByCnfg(String x, String y,String action ,String groupby) {
		
			if(x.equals("dateAppel")){
			
				Query q = em.createQuery("Select to_date("+x+",'YYMMDD') ,"+action+" "+y+" " +"From StatMsc s"+" "+groupby+" Order BY to_date(s.dateAppel,'YYMMDD') desc ");
				List<Object[]> resultList = q.getResultList().subList(0, 7);
				Map<String, Number> resultMap = new HashMap<String, Number>(resultList.size());
				for (Object[] result : resultList){
				  
					
						resultMap.put(result[0].toString(), (Number)result[1]);
					
				} 
				return resultMap;
			}else if(x.equals("typeDest")){
				Query q = em.createQuery("Select s.destination.typeDest," +action+" "+y+" " +"From StatMsc s   Group By s.destination.typeDest");
				List<Object[]> resultList = q.getResultList();
				System.out.println(resultList.size());
				Map<String, Number> resultMap = new HashMap<String, Number>(resultList.size());
				for (Object[] result : resultList){
					if(result[0]!=null && result[1]!=null){
						resultMap.put(result[0].toString(), (Number)result[1]);
					}
						
						
				} 
				return resultMap;
			}
			else{
				Query q = em.createQuery("Select "+x+" ,"+action+" "+y+" " +"From StatMsc s"+" "+groupby);
				List<Object[]> resultList = q.getResultList();
				Map<String, Number> resultMap = new HashMap<String, Number>(resultList.size());
				for (Object[] result : resultList){
					if(result[0]!=null && result[1]!=null){
						resultMap.put(result[0].toString(), (Number)result[1]);
					}
					
						
					
				} 
				return resultMap;
			}
		
		
		
	}

	@Override
	public Map<String, Number> getMscByFilters1(String x, String y,
			String action, String groupby, List<String> Where) {
		
			if(x.equals("s.destination.typeDest")|| x.equals("msc")){
				
				String where =  Where.get(0);
				if(Where.size()>=2){
					for(int i=1;i<Where.size();i++){
						where = where+" AND "+ Where.get(i);
					}
				}
				
				Query q = em.createQuery("Select "+x+"," +action+" "+y+" " +"From StatMsc s"+" Where "+where+" "+groupby);
				List<Object[]> resultList = q.getResultList();
				if(resultList.isEmpty()==false){
					Map<String, Number> resultMap = new HashMap<String, Number>(resultList.size());
					for (Object[] result : resultList){
						if(result[0]!=null && result[1]!=null){
							resultMap.put(result[0].toString(), (Number)result[1]);
						}
						
							
						
					} 
					return resultMap;	
				}else{
					return null;
				}	
			}
			
			
			else{
				String where =  Where.get(0);
			if(Where.size()>=2){
				for(int i=1;i<Where.size();i++){
					where = where+" AND "+ Where.get(i);
				}
			}
			
			Query q = em.createQuery("Select "+x+" ,"+action+" "+y+" " +"From StatMsc s"+" Where "+where+" "+groupby+"Order BY "+x+" DESC ");
			
				List<Object[]> resultList = q.getResultList();
				if(resultList.isEmpty()==false){
					Map<String, Number> resultMap = new HashMap<String, Number>(resultList.size());
					for (Object[] result : resultList){
						if(result[0]!=null && result[1]!=null){
							resultMap.put(result[0].toString(), (Number)result[1]);
						}
						
							
						
					} 
					return resultMap;	
				}else{
					return null;
				}
			}
		
		
	}

	@Override
	public Map<Object, Number> getMscByFilters(String x, String y,
			String action, String groupby, List<String> Where) {
		
			if(x.equals("dateAppel")){
				
				String where =  Where.get(0);
				if(Where.size()>=2){
					for(int i=1;i<Where.size();i++){
						where = where+" AND "+ Where.get(i);
					}
				}
				
				Query q = em.createQuery("Select"+x+","+action+" "+y+" " +"From StatMsc s"+" Where "+where+" "+groupby+" Order BY "+x+" DESC ");
				List<Object[]> resultList = q.getResultList();
				if(resultList.isEmpty()==false){
					Map<Object, Number> resultMap = new HashMap<Object, Number>(resultList.size());
					for (Object[] result : resultList){
						if(result[0]!=null && result[1]!=null){
							resultMap.put(result[0], (Number)result[1]);
						}
						
							
						
					} 
					return resultMap;	
				}else{
					return null;
				}
			}
			
			
			else{
				String where =  Where.get(0);
			if(Where.size()>=2){
				for(int i=1;i<Where.size();i++){
					where = where+" AND "+ Where.get(i);
				}
			}
			
			Query q = em.createQuery("Select "+x+" ,"+action+" "+y+" " +"From StatMsc s"+" Where "+where+" "+groupby+" Order BY "+x+" DESC ");
			
				List<Object[]> resultList = q.getResultList();
				if(resultList.isEmpty()==false){
					Map<Object, Number> resultMap = new HashMap<Object, Number>(resultList.size());
					for (Object[] result : resultList){
						if(result[0]!=null && result[1]!=null){
							resultMap.put(result[0], (Number)result[1]);
						}
						
							
						
					} 
					return resultMap;	
				}else{
					return null;
				}
			}
		
		
	}







	@Override
	public String getStatMsc() {
		// TODO Auto-generated method stub
		return em.find(StatMsc.class, 1).getDateAppel();
	}







	@Override
	public Date getMSCDate() {
		// TODO Auto-generated method stub
		return (Date) em.createQuery("SELECT to_date(s.dateAppel,'YYMMDD') from StatMsc s ").getSingleResult();
	}







	@Override
	public Map<Object, Number> getMscBytranche(String x, String y,
			String action, String groupby, List<String> Where) {
	
			
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
	public List<Object[]> getTest()  {
		

return em.createQuery("Select to_date(dateAppel,'YYMMDD'),SUM(duree) from StatMsc s Group By to_date(dateAppel,'YYMMDD')  )").getResultList();
		}

	@Override
	public List<Object[]> getTest1()  {
		

return em.createQuery("Select to_date(dateAppel,'YYMMDD'),SUM(nbAppel) from StatMsc s Group By to_date(dateAppel,'YYMMDD')  )").getResultList();
		}







	@Override
	public List<Integer> getAllYears() {
		// TODO Auto-generated method stub
		return em.createQuery("Select distinct extract(year from to_date(dateAppel,'YYMMDD'))From StatMsc s order by extract(year from to_date(dateAppel,'YYMMDD')) ").getResultList();
	}







	@Override
	public List<String> getTypeCall() {
		// TODO Auto-generated method stub
		return em.createQuery("Select distinct typeCall from StatMsc s").getResultList();
	}







	@Override
	public List<String> getTypeSubscriber() {
		// TODO Auto-generated method stub
		return em.createQuery("Select distinct typeSubscriber from StatMsc s").getResultList();
	}







	@Override
	public List<String> getAlltrancheHoraire() {
		// TODO Auto-generated method stub
		return em.createQuery("Select distinct trancheHoraire from StatMsc s").getResultList();
	}







	@Override
	public List<Object[]> getAllStatMsc(String x, List<String> list_y,
			String action, String groupby, List<String> Where) {
		
		String y = action+"("+list_y.get(0)+")";
		// TODO Auto-generated method stub
		if(list_y.size()>=2){
			for(int i=1;i<list_y.size();i++){
				y = y+" , "+action+"("+ list_y.get(i)+") ";
			}
		}
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		
		return em.createQuery("Select "+x+","+y+"From StatMsc s Where  "+where+" Group By "+x+" Order By "+x ).getResultList();
	}







	
		







	


}
