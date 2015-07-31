package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.reporting.metier.entities.AxeY;
import com.reporting.metier.entities.Result;
import com.reporting.metier.interfaces.GenericReportingRemote;

@Stateless
public class GenericReportingImpl implements GenericReportingRemote {

	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Result> BarOrLineGraphePreview(String x, List<AxeY> list_y,
			String table) {
		
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
		TypedQuery<Result> query = em.createQuery("Select New  com.reporting.metier.entities.Result("+x+" "+axeYOp+") From "+table+" Group By "+x+" Order By "+x,Result.class);

		List<Result> result =query.getResultList().subList(0, 7);;
		
		return result;
		
	}

}
