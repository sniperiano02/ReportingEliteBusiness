package com.reporting.metier.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;








import javax.sql.DataSource;







import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.reporting.metier.entities.StatMsc;
import com.reporting.metier.entities.StatTraficIn;
import com.reporting.metier.interfaces.LocalInRemote;


@Stateless
public class LocalINImpl implements LocalInRemote  {

	
	@PersistenceContext 
	EntityManager em;

	 private static SessionFactory factory; 
	
	
	
	@Override
	public Map<Object, Number> getConfiguartionChartByCfg(String x, String y,
			String action, String groupby) {
		if(x.equals("dateAppel")){
			
			Query q = em.createQuery("Select  to_date("+x+",'yyMMdd')  ,"+action+" "+y+" " +"From StatTraficIn s"+" "+groupby+" Order BY to_date(s.dateAppel,'YYMMDD') desc ");
			List<Object[]> resultList = q.getResultList().subList(0, 7);
			Map<Object, Number> resultMap = new HashMap<Object, Number>(resultList.size());
			for (Object[] result : resultList){
			  
				
					resultMap.put( result[0].toString(), (Number)result[1]);
				
			} 
			return resultMap;	
		}else if(x.equals("typeDest")){
			Query q = em.createQuery("Select s.destination.typeDest," +action+" "+y+" " +"From StatTraficIn s  where typeCdr='loc'  Group By s.destination.typeDest");
			List<Object[]> resultList = q.getResultList();
			System.out.println(resultList.size());
			Map<Object, Number> resultMap = new HashMap<Object, Number>(resultList.size());
			for (Object[] result : resultList){
					resultMap.put(result[0].toString(), (Number)result[1]);
			} 
			return resultMap;
		}else{
			
			Query q = em.createQuery("Select "+x+" ,"+action+" "+y+" " +"From StatTraficIn s where typeCdr='loc' "+" "+groupby);
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
	public Map<String, Number> getConfiguartionChartByCnfg(String x, String y,
			String action, String groupby) {
		if(x.equals("dateAppel")){
			
			Query q = em.createQuery("Select to_date("+x+",'YYMMDD') ,"+action+" "+y+" " +"From StatTraficIn s  where typeCdr='loc' "+" "+groupby+" Order BY to_date(s.dateAppel,'YYMMDD') desc ");
			List<Object[]> resultList = q.getResultList().subList(0, 7);
			Map<String, Number> resultMap = new HashMap<String, Number>(resultList.size());
			for (Object[] result : resultList){
			  
				
					resultMap.put(result[0].toString(), (Number)result[1]);
				
			} 
			return resultMap;
		}else if(x.equals("typeDest")){
			Query q = em.createQuery("Select s.destination.typeDest," +action+" "+y+" " +"From StatTraficIn s  where typeCdr='loc'   Group By s.destination.typeDest");
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
			Query q = em.createQuery("Select "+x+" ,"+action+" "+y+" " +"From StatTraficIn s  where typeCdr='loc' "+" "+groupby);
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
	public Map<Object, Number> getMscBytranche(String x, String y,
			String action, String groupby, List<String> Where) {
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		
		Query q = em.createQuery("Select CAST(trancheHoraire AS integer) ,"+action+" "+y+" " +"From StatTraficIn s"+" Where "+where+" "+"Group By CAST(trancheHoraire AS integer) Order by  CAST(trancheHoraire AS integer) DESC ");
	
	
		List<Object[]> resultList = q.getResultList();
		
		Map<Object, Number> resultMap = new HashMap<Object, Number>(resultList.size());
		for (Object[] result : resultList){
		  
			
				resultMap.put(result[0], (Number)result[1]);
			
		} 
		return resultMap;	
	
	
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
			
			Query q = em.createQuery("Select"+x+","+action+" "+y+" " +"From StatTraficIn s"+" Where  "+where+" "+groupby+" Order BY "+x+" DESC ");
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
		Query q = em.createQuery("Select "+x+" ,"+action+" "+y+" " +"From StatTraficIn s"+" Where  "+where+" "+groupby+" Order BY "+x+" DESC ");
		
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
	public List<Integer> getAllYears() {
		// TODO Auto-generated method stub
		return em.createQuery("Select distinct extract(year from to_date(dateAppel,'YYMMDD'))From StatTraficIn s order by extract(year from to_date(dateAppel,'YYMMDD')) ").getResultList();
	}

	@Override
	public List<String> getTypeCall() {
		// TODO Auto-generated method stub
		return em.createQuery("Select distinct typeCall from StatTraficIn s").getResultList();
	}

	@Override
	public List<String> getTypeSubscriber() {
		// TODO Auto-generated method stub
		return em.createQuery("Select distinct typeSubscriber from StatTraficIn s").getResultList();
	}

	@Override
	public List<String> getAlltrancheHoraire() {
		// TODO Auto-generated method stub
		return em.createQuery("Select distinct trancheHoraire from StatTraficIn s").getResultList();
	}

	@Override
	public List<Object[]> getAllStatLocalIn(String x, List<String> list_y,
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
		
		return em.createQuery("Select "+x+","+y+"From StatTraficIn s Where  "+where+" Group By "+x+" Order By "+x ).getResultList();
	
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
			
			Query q = em.createQuery("Select "+x+"," +action+" "+y+" " +"From StatTraficIn s"+" Where "+where+" "+groupby);
			;
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
		
		Query q = em.createQuery("Select "+x+" ,"+action+" "+y+" " +"From StatTraficIn s"+" Where  "+where+" "+groupby+"Order BY "+x+" DESC ");
		
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
	public List<Object[]> getAllStatIN(String x,List<String> Where) {
		
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		

		Query q = em.createQuery("Select "+x+" ,SUM(revenue)*60/SUM(duree),SUM(nbAppel),SUM(duree),SUM(revenue) From StatTraficIn s where  "+where+"  Group By "+x+" Order By "+x+" ASC");

				
			List<Object[]> resultList = q.getResultList();
			return resultList;
		
	}

	@Override
	public List<Object[]> getAllStatTrafficStatic(String x, String y,
			String action, String groupby, List<String> Where) {
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		if(x.contains("substring")){
			Query q=  em.createQuery("Select "+x+","+y+"From StatTraficIn s Where  "+where+" Group By trancheHoraire Order By CAST (trancheHoraire as integer)" );
			
			return q.getResultList();

		}else{

			
			long nosd =new Date().getTime();
			Query q=  em.createQuery("Select "+x+","+y+"From StatTraficIn s Where  "+where+" Group By "+x+" Order By "+x );
			System.out.println(new Date().getTime()-nosd);
	
			return q.getResultList();

		}
	}

	@Override
	public List<Object[]> getAllArpm(String x, String list_y, String action,
			String groupby, List<String> Where) {
		// TODO Auto-generated method stub
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		Query q = 	em.createNativeQuery("Select 'taxé' as taxe, sum(duree_taxe) as sum_duree  FROM stat.stat_taxe_prp   where  "+where+" UNION Select 'Non Taxé' as non_taxé ,SUM(duree_total)-SUM(duree_taxe)  as sum_duree  From stat.stat_taxe_prp  where  "+where);

				
		
		List<Object[]> resultList = q.getResultList();
			return resultList;
	}
}
