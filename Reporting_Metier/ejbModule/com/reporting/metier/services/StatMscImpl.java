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

import com.reporting.metier.entities.AxeY;
import com.reporting.metier.entities.Result;
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
	public List<Object[]> getConfiguartionChartByCfg(String x, String y,String table,String action ,String groupby) {
		
			if(x.contains("dateAppel")){
				
				Query q = em.createQuery("Select  cast (to_date("+x+",'YYMMDD') as text)  ,"+action+" "+y+" " +"From "+table+" "+groupby+" Order BY to_date(dateAppel,'YYMMDD') desc limit 7");
				q.setFirstResult(0);
				q.setMaxResults(7);
				List<Object[]> resultList = q.getResultList();
				
				return resultList;	
			}else {
				Query q = em.createQuery("Select "+x+" ," +action+" "+y+" " +"From "+table+"  Group By "+x);
				List<Object[]> resultList = q.getResultList();
				System.out.println(resultList.size());
				
				return resultList;
			}
				
		
		
	}
	



	@Override
	public List<Object[]> getConfiguartionChartByCnfg(String x, String y,String table,String action ,String groupby) {
		
			if(x.contains("dateAppel")){
			
				Query q = em.createQuery("Select cast (to_date("+x+",'YYMMDD') as text), ,"+action+" "+y+" " +"From "+table+" "+groupby+" Order BY to_date(dateAppel,'YYMMDD') desc ");
				q.setFirstResult(0);
				q.setMaxResults(7);
				List<Object[]> resultList = q.getResultList();
				return resultList;
			}else {
				Query q = em.createQuery("Select "+x+"," +action+" "+y+" " +"From "+table+"  s  "+groupby);
				List<Object[]> resultList = q.getResultList();
				System.out.println(resultList.size());
				
				return resultList;
			}
			
		
		
		
	}

	@Override
	public List<Object[]> getMscByFilters1(String x, String y,String table,
			String action, String groupby, List<String> Where) {
		
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}	
		List<Object[]> res = new ArrayList<Object[]>();
		if(x.contains("typeDest")|| x.contains("msc")){
				
				
				
				Query q = em.createQuery("Select "+x+"," +action+" "+y+" " +"From "+table+" Where "+where+" "+groupby);
			 res =  q.getResultList();
				return res;
				
			}else{
				
			
			Query q = em.createQuery("Select "+x+" ,"+action+" "+y+" " +"From "+table+" Where "+where+" "+groupby+" Order BY "+x+" DESC ");
			
				res = q.getResultList();
				return res;
			}
		
		
	}

	@Override
	public List<Object[]> getMscByFilters(String x, String y,String table,
			String action, String groupby, List<String> Where) {
		
			if(x.equals("dateAppel")){
				
				String where =  Where.get(0);
				if(Where.size()>=2){
					for(int i=1;i<Where.size();i++){
						where = where+" AND "+ Where.get(i);
					}
				}
				
				Query q = em.createQuery("Select"+x+","+action+" "+y+" " +"From "+table+" Where "+where+" "+groupby+" Order BY "+x+" DESC ");
				List<Object[]> resultList = q.getResultList();
				
					return resultList;	
				
			}
			
			
			else{
				String where =  Where.get(0);
			if(Where.size()>=2){
				for(int i=1;i<Where.size();i++){
					where = where+" AND "+ Where.get(i);
				}
			}
			
			Query q = em.createQuery("Select "+x+" ,"+action+" "+y+" " +"From "+table+" Where "+where+" "+groupby+"  Order BY "+x+" DESC ");
			
				List<Object[]> resultList = q.getResultList();
				return resultList;
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
		return (Date) em.createQuery("SELECT to_date(dateAppel,'YYMMDD') from StatMsc s ").getSingleResult();
	}







	@Override
	public List<Object[]> getMscBytranche(String x, String y,String table,
			String action, String groupby, List<String> Where) {
	
			
			String where =  Where.get(0);
			if(Where.size()>=2){
				for(int i=1;i<Where.size();i++){
					where = where+" AND "+ Where.get(i);
				}
			}
			
			Query q = em.createQuery("Select CAST(trancheHoraire AS integer) ,"+action+" "+y+" " +"From "+table+" Where "+where+" "+"Group By CAST(trancheHoraire AS integer) Order by  CAST(trancheHoraire AS integer) DESC ");
			List<Object[]> resultList = q.getResultList();
			
			return resultList;	
		
		
	
	
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
	public List<Object[]> getAllStatMsc(String x, List<String> list_y,String table,
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
		
		return em.createQuery("Select "+x+","+y+"From "+table+ " Where  "+where+" Group By "+x+ " Order By "+x ).getResultList();
	}







	@Override
	public List<Object[]> getAllStatMsc1(String x, List<AxeY> list_y,String table,
			String action, String groupby, List<String> Where) {
		String axeYOp="";
				
		for(int j=0;j<list_y.get(0).getOperations().size();j++){
			axeYOp = axeYOp+","+list_y.get(0).getOperations().get(j)+"("+ list_y.get(0).getAxey()+")";
		}
		if(list_y.size()>=2){
			for(int k =1;k<list_y.size();k++){
				String y ="";
				for(int j=0;j<list_y.get(k).getOperations().size();j++){
					y =y+","+ list_y.get(k).getOperations().get(j)+"("+ list_y.get(k).getAxey()+")";
				}
				axeYOp= axeYOp+y;
				
			}
		}
		String where="";
		if(Where.size()>0){
			
		 where ="Where "+Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		}
		return em.createQuery("Select "+x+" "+axeYOp+"From "+table+"   "+where+" Group By "+x+" Order By "+x ).getResultList();
	}







	@Override
	public Map<Object, Number> getConfiguartionChartGeneric(String x, String y,
			String table, String action, String groupby) {
		// TODO Auto-generated method stub
		Query q = em.createQuery("Select "+x+" ,"+action+" "+y+" " +"From"+table+" t "+" "+groupby);
		List<Object[]> resultList = q.getResultList();
		Map<Object, Number> resultMap = new HashMap<Object, Number>(resultList.size());
		for (Object[] result : resultList){
			if(result[0]!=null && result[1]!=null){
				resultMap.put(result[0].toString(), (Number)result[1]);
			}
			
				
			
		} 
		return resultMap;	
	}







	@Override
	public List<Object[]> getAllStatMscStatic(String x, String y,
			String action, String groupby, List<String> Where) {
		// TODO Auto-generated method stub
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		List<Object[]> res= new ArrayList<Object[]>();
		if(x.contains("substring")){
		
			Query q=  em.createQuery("Select "+x+","+y+"From StatMsc s Where  "+where+" Group By trancheHoraire Order By CAST (trancheHoraire as integer)" );
			res=q.getResultList();
			if(!res.isEmpty()){
				return res;
			}else
				return null;
		

		}else{
			Query q=  em.createQuery("Select "+x+","+y+"From StatMsc s Where  "+where+" Group By "+x+" Order By "+x );
			res=q.getResultList();
			if(!res.isEmpty()){
				return res;
			}else
				return new ArrayList<Object[]>();
		
			

		}
		
		
	}







	@Override
	public List<Object> getFilterDitinct(String x, String table) {
		// TODO Auto-generated method stub
		return em.createQuery("Select Distinct "+x+" From "+table).getResultList();
	}







	@Override
	public List<Object[]> getFilter(String x, String table) {
		// TODO Auto-generated method stub
		return em.createQuery("Select  "+x+" From "+table).getResultList();
	}







	













}












	
		







	



