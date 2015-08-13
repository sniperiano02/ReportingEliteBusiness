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
import com.reporting.metier.entities.StatCtiInter;
import com.reporting.metier.interfaces.StatCtiImplRemote;


/**
 * Session Bean implementation class StatCtiImpl
 */
@Stateless
public class StatCtiImpl implements StatCtiImplRemote {

	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public StatCtiImpl() {
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
	public List<Object[]> getCtiByFilters1(String x, String y,String table,
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
	public List<Object[]> getCtiByFilters(String x, String y,String table,
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
	public String getStatCti() {
		// TODO Auto-generated method stub
		return em.find(StatCtiInter.class, 1).getDateAppel();
	}







	@Override
	public Date getCtiDate() {
		// TODO Auto-generated method stub
		return (Date) em.createQuery("SELECT to_date(dateAppel,'YYMMDD') from StatCtiInter s ").getSingleResult();
	}







	@Override
	public List<Object[]> getCtiBytranche(String x, String y,String table,
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
		

return em.createQuery("Select to_date(dateAppel,'YYMMDD'),SUM(duree) from StatCtiInter s Group By to_date(dateAppel,'YYMMDD')  )").getResultList();
		}

	@Override
	public List<Object[]> getTest1()  {
		

return em.createQuery("Select to_date(dateAppel,'YYMMDD'),SUM(nbAppel) from StatCtiInter s Group By to_date(dateAppel,'YYMMDD')  )").getResultList();
		}







	@Override
	public List<Integer> getAllYears() {
		// TODO Auto-generated method stub
		return em.createQuery("Select distinct extract(year from to_date(dateAppel,'YYMMDD'))From StatCtiInter s order by extract(year from to_date(dateAppel,'YYMMDD')) ").getResultList();
	}








	@Override
	public List<String> getAlltrancheHoraire() {
		// TODO Auto-generated method stub
		return em.createQuery("Select distinct trancheHoraire from StatCtiInter s").getResultList();
	}







	@Override
	public List<Object[]> getAllStatCti(String x, List<String> list_y,String table,
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
		
		return em.createQuery("Select "+x+","+y+"From StatCtiInter s Where  "+where+" Group By "+x+ " Order By "+x ).getResultList();
	}







	@Override
	public List<Object[]> getAllStatCti1(String x, List<AxeY> list_y,String table,
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
	public List<Object[]> getAllStatCtiStatic(String x, String y,
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
		
			Query q=  em.createQuery("Select "+x+","+y+"From StatCtiInter s Where  "+where+" Group By trancheHoraire Order By CAST (trancheHoraire as integer)" );
			res=q.getResultList();
			if(!res.isEmpty()){
				return res;
			}else
				return null;
		

		}else{
			Query q=  em.createQuery("Select "+x+","+y+"From StatCtiInter s Where  "+where+" Group By "+x+" Order By "+x );
			res=q.getResultList();
			if(!res.isEmpty()){
				return res;
			}else
				return null;
		
			

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







	
	@Override
	public List<Object[]> getAllQualite(String x, String list_y, String action,
			String groupby, List<String> Where) {
		// TODO Auto-generated method stub
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		Query q = 	em.createNativeQuery("Select 'Taux reussi' as taux, round((coalesce(sum(nombre_reussi),0)/coalesce(sum(nombre_total),1))*100,3)as asr  FROM stat.stat_cti_inter   where  "+where+" UNION Select 'Taux non reussi' as taux ,round( 100-((coalesce(sum(nombre_reussi),0)/coalesce(sum(nombre_total),1))*100),3) as asr  From stat.stat_cti_inter  where  "+where);

				
		
		List<Object[]> resultList = q.getResultList();
			return resultList;
	}




	@Override
	public List<Object[]> getAllStatQualite(String x,List<String> Where) {
		
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		

		Query q = em.createQuery("Select "+x+" ,SUM(nombreTotal),SUM(dureeTotal),SUM(nombreReussi),round((coalesce(sum(nombreReussi),0)/coalesce(sum(nombreTotal),1))*100,3) From StatCtiInter s where  "+where+"  Group By "+x+" Order By "+x+" ASC");

				
			List<Object[]> resultList = q.getResultList();
			return resultList;
		
	}







}












	
		







	



