package com.reporting.metier.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.reporting.metier.entities.StatVerifTarifRoamingin;
import com.reporting.metier.interfaces.StatVerifTarifRoaRemote;


@Stateless
public class StatVerifTarifRoaming implements StatVerifTarifRoaRemote {

	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Object[]> getStatVerifTarifRoa(String x, String list_y,
			String groupby, List<String> Where) {
		
		String where =  Where.get(0);
		if(Where.size()>=2){
			for(int i=1;i<Where.size();i++){
				where = where+" AND "+ Where.get(i);
			}
		}
		
			Query q=  em.createQuery("Select "+x+","+list_y+" From StatVerifTarifRoamingin  Where  "+where+" Group By "+x+" Order By "+x );
			
			return q.getResultList();

	}
	@Override
	public List<Integer> getAllYears() {
		// TODO Auto-generated method stub
		return em.createQuery("Select  distinct extract(year from to_date(dateAppel,'YYMMDD'))From StatVerifTarifRoamingin  order by extract(year from to_date(dateAppel,'YYMMDD')) ").getResultList();
		}
	@Override
	public List<Object[]> getDetailsStatVerifTarifRoa(String Where) {
		// TODO Auto-generated method stub
		
	Query q=  em.createQuery("Select operateur.operateur,dest,typeCall,SUM(tarifAppliquer),SUM(tarifCommmercial),SUM(nbAppel),SUM(impact) From StatVerifTarifRoamingin Where  "+Where +" Group By operateur.operateur,dest ");
	
		return q.getResultList();
	}
}
